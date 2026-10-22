package vn.tht.launcher.home

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import vn.tht.launcher.R
import vn.tht.launcher.media.NowPlaying
import vn.tht.launcher.radio.EcarxRadio
import vn.tht.launcher.ui.Dialogs
import vn.tht.launcher.ui.IconList
import vn.tht.launcher.ui.Icons
import vn.tht.launcher.ui.textIfChanged
import vn.tht.launcher.ui.Palette
import vn.tht.launcher.ui.Ui

/**
 * Ô radio. Trên đầu xe ECARX: điều khiển thẳng dịch vụ radio ([EcarxRadio]) – hiện băng/tần số,
 * tên đài (từ media center), ◀ ▶ chuyển đài yêu thích (chưa có thì dò từng bước), ▶ phát.
 * Nhấn giữ: đài yêu thích, dò, nhập tần số, đổi FM/AM. Không có dịch vụ radio thì là nút mở app radio.
 */
class RadioWidget(host: WidgetHost) : HomeWidget(host) {
    private val radio: EcarxRadio get() = host.radio
    private val station = auto(Ui.text(c, "Radio", 22f, bold = true), 14, 30).apply { ellipsize = android.text.TextUtils.TruncateAt.END }
    private val sub = Ui.text(c, "Chạm để mở", 14f, dim = true).apply { maxLines = 1 }
    private val fav = ImageView(c).apply {
        val p = Ui.dp(c, 4)
        setPadding(p, p, p, p)
        setOnClickListener { radio.state?.let { radio.toggleFavorite(it.freqKHz) } }
    }
    private val play = btn("play") { if (radio.available) { if (radio.state?.playing == true) host.mediaPlayPause() else radio.play() } else host.openRadio() }
    /** Tên đài/bài từ media center khi radio đang phát. */
    private var stationName: String? = null
    private var lastNp: NowPlaying? = null
    private val listener: (EcarxRadio.State?) -> Unit = { render() }

    override val view: View = Ui.hbox(c).apply {
        background = cardBackground(this)
        val p = Ui.dp(c, 12)
        setPadding(p, p, p, p)
        addView(android.widget.FrameLayout(c).apply {
            background = Ui.pill(c, Palette.accent, 14)
            val q = Ui.dp(c, 10)
            setPadding(q, q, q, q)
            addView(ImageView(c).apply { setImageDrawable(Icons.get(c, "radio", android.graphics.Color.WHITE)) })
        }, LinearLayout.LayoutParams(Ui.dp(c, 56), Ui.dp(c, 56)).apply { marginEnd = Ui.dp(c, 12) })
        addView(Ui.vbox(c, gravity = Gravity.CENTER_VERTICAL).apply {
            addView(Ui.hbox(c).apply {
                addView(station, LinearLayout.LayoutParams(0, Ui.dp(c, 32), 1f))
                addView(fav, LinearLayout.LayoutParams(Ui.dp(c, 30), Ui.dp(c, 30)))
            }, matchW())
            addView(sub, matchW())
        }, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f))
        addView(btn("previous") { if (radio.available) radio.nextFavorite(-1) else host.openRadio() })
        addView(play)
        addView(btn("next") { if (radio.available) radio.nextFavorite(+1) else host.openRadio() })
        setOnClickListener { host.openRadio() }
        setOnLongClickListener { if (radio.available) menu() else host.openRadio(); true }
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) = radio.addListener(listener)
            override fun onViewDetachedFromWindow(v: View) = radio.removeListener(listener)
        })
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
        lastNp = np
        stationName = np?.takeIf { it.pkg?.contains("radio", ignoreCase = true) == true }?.title?.takeIf { it.isNotBlank() }
        render()
    }

    override fun onTick() { if (!radio.available && lastNp == null) render() }

    private fun render() {
        val s = radio.state
        if (radio.available && s != null) {
            station.textIfChanged = s.label()
            val favs = radio.favorites().sorted()
            val idx = favs.indexOf(s.freqKHz)
            sub.textIfChanged = listOfNotNull(
                stationName?.takeIf { it != s.label() },
                if (idx >= 0) "Yêu thích ${idx + 1}/${favs.size}" else null,
                if (!s.playing) "Chạm ▶ để nghe" else null,
            ).joinToString(" · ").ifEmpty { "Radio" }
            play.setImageDrawable(Icons.get(c, if (s.playing) "pause" else "play"))
            fav.visibility = View.VISIBLE
            fav.setImageDrawable(Icons.get(c, if (idx >= 0) "favorite-on" else "favorite-off", if (idx >= 0) Palette.accent else Palette.textDim))
            return
        }
        fav.visibility = View.GONE
        val np = lastNp
        if (np?.pkg?.contains("radio", ignoreCase = true) == true) {
            station.textIfChanged = np.title?.takeIf { it.isNotBlank() } ?: "Radio"
            sub.textIfChanged = listOfNotNull(np.artist, np.source).joinToString(" · ")
            play.setImageDrawable(Icons.get(c, if (np.isPlaying) "pause" else "play"))
        } else {
            station.textIfChanged = "Radio"
            sub.textIfChanged = host.radioLabel()?.let { "Chạm để mở $it" } ?: "Không tìm thấy app radio"
            play.setImageDrawable(Icons.get(c, "play"))
        }
    }

    /** Bảng tuỳ chọn khi nhấn giữ. */
    private fun menu() {
        val s = radio.state
        val favs = radio.favorites().sorted()
        val isFav = s != null && s.freqKHz in favs
        val actions = mutableListOf<Pair<IconList.Item, () -> Unit>>()
        if (s != null) actions += IconList.Item(if (isFav) "Bỏ ${s.label()} khỏi yêu thích" else "Thêm ${s.label()} vào yêu thích",
            if (isFav) "favorite-off" else "favorite-on") to { radio.toggleFavorite(s.freqKHz) }
        actions += IconList.Item("Đài yêu thích", "favorite-on", summary = if (favs.isEmpty()) "Chưa có" else "${favs.size} đài") to { pickFavorite(favs) }
        actions += IconList.Item("Dò lên", "chevron-up", summary = "FM 0,1 MHz · AM 9 kHz") to { radio.step(+1) }
        actions += IconList.Item("Dò xuống", "chevron-down") to { radio.step(-1) }
        actions += IconList.Item("Nhập tần số", "radio") to { enterFrequency() }
        actions += IconList.Item(if (s?.fm != false) "Chuyển sang AM" else "Chuyển sang FM", "dual") to { radio.switchBand() }
        actions += IconList.Item("Mở app radio", "apps") to { host.openRadio() }
        IconList.show(c, "Radio", actions.map { it.first }, columns = 2) { actions[it].second() }
    }

    private fun pickFavorite(favs: List<Int>) {
        if (favs.isEmpty()) return host.toast("Chưa có đài yêu thích – chạm ☆ trên ô radio để thêm")
        IconList.show(c, "Đài yêu thích", favs.map { f ->
            IconList.Item(EcarxRadio.State(if (f >= 30000) 1 else 0, f, 0).label(), "radio")
        }, columns = 3) { radio.tune(favs[it]) }
    }

    private fun enterFrequency() {
        Dialogs.form(c, "Nhập tần số", listOf(Dialogs.Field("MHz (FM) hoặc kHz (AM)", hint = "Ví dụ 99,9 hoặc 702", decimal = true)), listOf(
            Dialogs.FormButton("Huỷ"),
            Dialogs.FormButton("Nghe", Dialogs.Kind.PRIMARY) { (raw) ->
                val v = raw.replace(',', '.').toFloatOrNull()
                val kHz = when {
                    v == null -> null
                    v in 87.5f..108f -> Math.round(v * 10) * 100
                    v in 531f..1602f -> v.toInt()
                    else -> null
                }
                if (kHz == null) host.toast("FM 87,5–108 MHz, AM 531–1602 kHz") else radio.tune(kHz)
            },
        ))
    }
}
