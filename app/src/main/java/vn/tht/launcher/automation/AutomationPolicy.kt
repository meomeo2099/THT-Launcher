package vn.tht.launcher.automation

/** Pure scheduling rules: no Android or vehicle SDK calls, so lifecycle races can be tested. */
internal class AutomationCycle {
    data class Token internal constructor(val generation: Long, val configuration: Int)

    @Volatile private var generation = 0L

    fun invalidate() { generation++ }

    fun token(configuration: Int) = Token(generation, configuration)

    fun permits(
        token: Token,
        configuration: Int,
        enabled: Boolean,
        engineOn: Boolean?,
        observationAgeMs: Long,
    ): Boolean = token.generation == generation && token.configuration == configuration &&
        enabled && engineOn == true && observationAgeMs in 0L..MAX_OBSERVATION_AGE_MS

    companion object {
        const val MAX_OBSERVATION_AGE_MS = 10_000L

        fun freshStart(lastRunMs: Long, lastStopMs: Long, lastBootCount: Int?, currentBootCount: Int?): Boolean =
            currentBootCount != null && currentBootCount != lastBootCount || lastRunMs == 0L || lastStopMs > lastRunMs

        fun parkedLongEnough(nowMs: Long, stoppedAtMs: Long, requiredMinutes: Int): Boolean {
            if (requiredMinutes <= 0) return true
            // An absent or future timestamp is not evidence of a long enough parked interval.
            if (stoppedAtMs <= 0 || nowMs < stoppedAtMs) return false
            return nowMs - stoppedAtMs >= requiredMinutes.coerceAtMost(24 * 60) * 60_000L
        }

        fun validTemperature(sensor: Float?, cached: Float?, cacheAgeMs: Long): Float? =
            sensor?.takeIf { it.isFinite() && it in -60f..70f }
                ?: cached?.takeIf { it.isFinite() && it in -60f..70f && cacheAgeMs in 0L..10_800_000L }
    }
}

/** Close once above the limit, then rearm only after dropping below a five-km/h hysteresis. */
internal class RoofSpeedRule {
    private var armed = true
    private var pending = false
    private var lastAttemptAt = Long.MIN_VALUE

    fun reset() {
        armed = true
        pending = false
        lastAttemptAt = Long.MIN_VALUE
    }

    fun shouldClose(speedKmh: Float?, limitKmh: Int, nowMs: Long): Boolean {
        val speed = speedKmh?.takeIf { it.isFinite() && it in 0f..300f } ?: return false
        if (limitKmh !in 1..120) return false
        if (speed < (limitKmh - 5).coerceAtLeast(0)) armed = true
        if (!armed || pending || speed < limitKmh) return false
        if (lastAttemptAt != Long.MIN_VALUE && nowMs - lastAttemptAt < RETRY_MS) return false
        pending = true
        lastAttemptAt = nowMs
        return true
    }

    fun completed(accepted: Boolean) {
        pending = false
        if (accepted) armed = false
    }

    companion object { private const val RETRY_MS = 5_000L }
}
