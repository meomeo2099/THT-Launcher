package vn.tht.launcher.home

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.R
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui

/**
 * Ô radio: khi app radio đang là nguồn phát (phiên media của gói có chữ "radio"), hiện tên đài/bài
 * và nút chuyển đài; nếu không thì là nút mở app radio. Chưa điều khiển trực tiếp dịch vụ radio
 * ECARX (chưa có API được kiểm chứng trên xe).
 */
class RadioWidget(host: WidgetHost) : HomeWidget(host) {
    private val station = auto(Ui.text(c, "Radio", 22f, bold = true), 14, 30).apply { ellipsize = android.text.TextUtils.TruncateAt.END }
    private val sub = Ui.text(c, "Chạm để mở", 14f, dim = true).apply { maxLines = 1 }
    private val play = btn("play") { if (active) host.mediaPlayPause() else host.openRadio() }
    private var active = false

    override val view: View = Ui.hbox(c).apply {
        background = Ui.card(c)
        val p = Ui.dp(c, 12)
        setPadding(p, p, p, p)
        addView(android.widget.FrameLayout(c).apply {
            background = Ui.pill(c, Palette.accent, 14)
            val q = Ui.dp(c, 10)
            setPadding(q, q, q, q)
            addView(ImageView(c).apply { setImageDrawable(Icons.get(c, "radio", android.graphics.Color.WHITE)) })
        }, LinearLayout.LayoutParams(Ui.dp(c, 56), Ui.dp(c, 56)).apply { marginEnd = Ui.dp(c, 12) })
        addView(Ui.vbox(c, gravity = Gravity.CENTER_VERTICAL).apply {
            addView(station, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Ui.dp(c, 32)))
            addView(sub, matchW())
        }, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f))
        addView(btn("previous") { if (active) host.mediaPrevious() else host.openRadio() })
        addView(play)
        addView(btn("next") { if (active) host.mediaNext() else host.openRadio() })
        setOnClickListener { host.openRadio() }
    }

    private fun btn(key: String, onClick: () -> Unit) = ImageView(c).apply {
        tag = key
        setImageDrawable(Icons.get(c, key))
        val p = Ui.dp(c, 10)
        setPadding(p, p, p, p)
        layoutParams = LinearLayout.LayoutParams(Ui.dp(c, 48), Ui.dp(c, 48))
        background = c.getDrawable(R.drawable.ripple)
        setOnClickListener { onClick() }
    }

    override fun onMedia(np: NowPlaying?) {
        active = np?.pkg?.contains("radio", ignoreCase = true) == true
        if (active) {
            station.text = np!!.title?.takeIf { it.isNotBlank() } ?: "Radio"
            sub.text = listOfNotNull(np.artist, np.source).joinToString(" · ")
            play.setImageDrawable(Icons.get(c, if (np.isPlaying) "pause" else "play"))
        } else {
            station.text = "Radio"
            sub.text = host.radioLabel()?.let { "Chạm để mở $it" } ?: "Không tìm thấy app radio"
            play.setImageDrawable(Icons.get(c, "play"))
        }
    }

    override fun onTick() { if (!active) sub.text = host.radioLabel()?.let { "Chạm để mở $it" } ?: "Không tìm thấy app radio" }
}
