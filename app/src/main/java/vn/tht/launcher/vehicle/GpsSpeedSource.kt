package vn.tht.launcher.vehicle

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock

/** Opt-in speed fallback. Positions are never persisted or sent to a server by this helper. */
class GpsSpeedSource(private val context: Context) {
    private val manager = context.getSystemService(LocationManager::class.java)
    @Volatile private var sample: Location? = null
    private var listening = false
    private val listener = object : LocationListener {
        override fun onLocationChanged(location: Location) { sample = Location(location) }
        @Deprecated("Deprecated in Java")
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) { sample = null }
    }

    fun setEnabled(enabled: Boolean) {
        val permitted = context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (!enabled || !permitted) { stop(); return }
        if (listening || manager == null) return
        runCatching {
            sample = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let(::Location)
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 0f, listener, Looper.getMainLooper())
            listening = true
        }.onFailure { stop() }
    }

    @Suppress("DEPRECATION")
    fun speedKmh(): Float? {
        val value = sample ?: return null
        return GpsSpeedPolicy.accept(value.speed, value.hasSpeed(), value.isFromMockProvider,
            value.elapsedRealtimeNanos, SystemClock.elapsedRealtimeNanos())
    }

    fun stop() {
        if (listening) runCatching { manager?.removeUpdates(listener) }
        listening = false
        sample = null
    }
}

internal object GpsSpeedPolicy {
    fun accept(metersPerSecond: Float, hasSpeed: Boolean, mock: Boolean, atNanos: Long, nowNanos: Long): Float? {
        if (!hasSpeed || mock || !metersPerSecond.isFinite() || atNanos <= 0 || nowNanos < atNanos) return null
        if (nowNanos - atNanos > 5_000_000_000L) return null
        return (metersPerSecond * 3.6f).takeIf { it.isFinite() && it in 0f..300f }
    }
}
