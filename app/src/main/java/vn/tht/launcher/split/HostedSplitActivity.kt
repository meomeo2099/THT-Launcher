package vn.tht.launcher.split

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import vn.tht.launcher.home.EmbeddedMap
import vn.tht.launcher.system.AppWindowPolicy
import vn.tht.launcher.system.ExternalAppLauncher
import vn.tht.launcher.ui.ContentInsets
import vn.tht.launcher.ui.Ui
import java.lang.ref.WeakReference

/** Two owned virtual displays inside one fullscreen THT window. No native docked stack/divider. */
class HostedSplitActivity : Activity() {
    private var maps = emptyList<EmbeddedMap>()
    private lateinit var leftBox: FrameLayout
    private lateinit var rightBox: FrameLayout
    private lateinit var row: LinearLayout
    private var request: Split? = null
    private var token = ""
    private var ratio = 50
    private var released = false
    private var leftReady = false
    private var rightReady = false

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        token = intent.getStringExtra("token") ?: return finish()
        if (expectedToken != token) return finish()
        val s = Split(intent.getStringExtra("id") ?: "", intent.getStringExtra("left") ?: "",
            intent.getStringExtra("right") ?: "", intent.getIntExtra("ratio", 50))
        if (!supported(this) || s.left == s.right || s.left.isBlank() || s.right.isBlank()) return finish()
        request = s; ratio = state?.getInt("ratio") ?: s.ratio
        active = WeakReference(this)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        fullscreen()
        leftBox = FrameLayout(this); rightBox = FrameLayout(this)
        row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            addView(leftBox, LinearLayout.LayoutParams(0, -1, ratio.toFloat()))
            addView(LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(0xff202630.toInt())
                addView(Ui.button(this@HostedSplitActivity, "×") { finish() }, LinearLayout.LayoutParams(-1, Ui.dp(context, 48)))
                addView(Ui.button(this@HostedSplitActivity, "⇄") { swap() }, LinearLayout.LayoutParams(-1, Ui.dp(context, 48)))
                addView(TextView(context).apply {
                text = "⋮"; gravity = Gravity.CENTER; textSize = 24f
                setTextColor(0xffffffff.toInt()); setBackgroundColor(0xff202630.toInt())
                var start = ratio
                setOnTouchListener { _, e ->
                    when (e.actionMasked) {
                        MotionEvent.ACTION_DOWN -> start = ratio
                        MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                            val xy = IntArray(2).also(row::getLocationOnScreen)
                            setRatio(((e.rawX - xy[0]) * 100 / row.width.coerceAtLeast(1)).toInt())
                            if (e.actionMasked == MotionEvent.ACTION_UP) saveRatio()
                        }
                        MotionEvent.ACTION_CANCEL -> setRatio(start)
                    }
                    true
                }
                }, LinearLayout.LayoutParams(-1, 0, 1f))
            }, LinearLayout.LayoutParams(Ui.dp(this@HostedSplitActivity, 48), -1))
            addView(rightBox, LinearLayout.LayoutParams(0, -1, (100 - ratio).toFloat()))
        }
        val canvas = FrameLayout(this).apply {
            setBackgroundColor(0xff10151d.toInt())
            addView(row, FrameLayout.LayoutParams(-1, -1))
            setPadding(0, 0, 0, intent.getIntExtra("bottom", 0).coerceAtLeast(0))
            ContentInsets.bind(this)
        }
        setContentView(canvas)
        try {
            val left = EmbeddedMap(this, requireFocus = false, onStarted = { leftReady = true; ready() }) { failed(it) }
            val right = EmbeddedMap(this, requireFocus = false, onStarted = { rightReady = true; ready() }) { failed(it) }
            maps = listOf(left, right)
            leftBox.addView(left, FrameLayout.LayoutParams(-1, -1)); rightBox.addView(right, FrameLayout.LayoutParams(-1, -1))
            if (!left.create() || !right.create()) { failed("Không tạo được hai màn hình ứng dụng"); return }
            left.start(packageManager.getLaunchIntentForPackage(s.left) ?: error("App trái không mở được"))
            right.start(packageManager.getLaunchIntentForPackage(s.right) ?: error("App phải không mở được"))
        } catch (t: Exception) { failed(t.message ?: "Không mở được cặp ứng dụng") }
    }
    private fun fullscreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
    override fun onWindowFocusChanged(focus: Boolean) { super.onWindowFocusChanged(focus); if (focus) fullscreen() }
    override fun onResume() { super.onResume(); maps.forEach { it.setActive(true) } }
    // Android 9 may stop the host when a child virtual display gets focus. The owned
    // surfaces must still finish their launch/rebuild; closeNow is the lifecycle boundary.
    override fun onSaveInstanceState(out: Bundle) { out.putInt("ratio", ratio); super.onSaveInstanceState(out) }
    private fun ready() { if (leftReady && rightReady && !released) listener?.invoke(token, true) }
    private fun failed(why: String) { Toast.makeText(this, why, Toast.LENGTH_LONG).show(); closeNow() }
    fun setRatio(value: Int) {
        ratio = value.coerceIn(20, 80)
        (leftBox.layoutParams as LinearLayout.LayoutParams).weight = ratio.toFloat()
        (rightBox.layoutParams as LinearLayout.LayoutParams).weight = (100 - ratio).toFloat()
        row.requestLayout()
    }
    private fun saveRatio() { request?.let { SplitStore.get(this, it.id)?.let { saved -> SplitStore.put(this, saved.copy(ratio = ratio)) } } }
    private fun swap() {
        val s = request ?: return
        request = s.copy(left = s.right, right = s.left, ratio = 100 - ratio)
        maps = maps.reversed()
        leftBox.removeAllViews(); rightBox.removeAllViews()
        // Recreate rather than reattach released SurfaceViews.
        closeNow()
        val next = request!!
        SplitStore.get(this, next.id)?.let { SplitStore.put(this, next) }
        pendingSwap?.invoke(next)
    }
    fun closeNow() {
        if (!released) {
            released = true
            val ownedWindow = active?.get() === this
            maps.forEach { it.release() }
            request?.let { ExternalAppLauncher.hostedDetached(setOf(it.left, it.right)) }
            if (active?.get() === this) active = null
            listener?.invoke(token, false)
            if (ownedWindow) AppWindowPolicy.apply(this, setOf(packageName))
        }
        if (!isFinishing) super.finish()
    }
    override fun finish() { closeNow() }
    override fun onDestroy() { closeNow(); super.onDestroy() }
    companion object {
        private var active: WeakReference<HostedSplitActivity>? = null
        var listener: ((String, Boolean) -> Unit)? = null
        var pendingSwap: ((Split) -> Unit)? = null
        var expectedToken: String? = null
        fun supported(c: Context): Boolean = c.packageManager.hasSystemFeature(PackageManager.FEATURE_ACTIVITIES_ON_SECONDARY_DISPLAYS) &&
            c.checkSelfPermission("android.permission.ACTIVITY_EMBEDDING") == PackageManager.PERMISSION_GRANTED &&
            c.checkSelfPermission("android.permission.REMOVE_TASKS") == PackageManager.PERMISSION_GRANTED &&
            c.checkSelfPermission("android.permission.MANAGE_ACTIVITY_STACKS") == PackageManager.PERMISSION_GRANTED
        fun close(token: String) { active?.get()?.takeIf { it.token == token }?.closeNow() }
        fun resize(token: String, ratio: Int): Boolean = active?.get()?.takeIf { it.token == token }?.let { it.setRatio(ratio); true } ?: false
    }
}
