package vn.tht.launcher.split

import android.content.Context
import vn.tht.launcher.ui.Dialogs
import vn.tht.launcher.ui.IconList

/** Hộp thoại tạo / chỉnh cặp chia đôi (theo theme launcher). */
class SplitUi(private val c: Context, private val host: Host) {
    interface Host {
        val splitScreen: SplitScreen
        fun pickApp(onPicked: (String) -> Unit)
        fun appLabel(pkg: String): String?
        fun toast(msg: String)
        fun addSplitWidget(id: String)
        fun addSplitToDock(split: Split)
        /** Cặp đã đổi/xoá: vẽ lại widget và nút liên quan. */
        fun splitsChanged()
    }

    fun label(s: Split) = "${name(s.left)} | ${name(s.right)}"

    private fun name(pkg: String) = host.appLabel(pkg) ?: pkg

    /** Tạo cặp mới: chọn app trái rồi app phải. */
    fun create(onCreated: (Split) -> Unit) {
        host.toast("Chọn app bên trái")
        host.pickApp { left ->
            host.toast("Chọn app bên phải")
            host.pickApp { right ->
                if (left == right) return@pickApp host.toast("Chọn hai app khác nhau")
                val s = Split.new(left, right)
                SplitStore.put(c, s)
                onCreated(s)
            }
        }
    }

    /** Chọn một cặp có sẵn hoặc tạo mới. */
    fun choose(onPicked: (Split) -> Unit) {
        val all = SplitStore.all(c)
        if (all.isEmpty()) return create(onPicked)
        IconList.show(c, "Chia đôi màn hình",
            all.map { IconList.Item(label(it), "dual", summary = "Tỉ lệ ${it.ratio} : ${100 - it.ratio}") } +
                IconList.Item("Cặp mới", "plus", summary = "Chọn app bên trái và bên phải")) { i ->
            if (i < all.size) onPicked(all[i]) else create(onPicked)
        }
    }

    /** Bảng cài đặt một cặp. */
    fun edit(id: String) {
        val s = SplitStore.get(c, id) ?: return host.toast("Cặp chia đôi này đã bị xoá")
        val open = host.splitScreen.openId == s.id
        IconList.show(c, "Chia đôi: ${label(s)}", listOf(
            IconList.Item("App bên trái", "chevron-left", summary = name(s.left)),
            IconList.Item("App bên phải", "chevron-right", summary = name(s.right)),
            IconList.Item("Tỉ lệ", "dual", summary = "${s.ratio} : ${100 - s.ratio}"),
            IconList.Item("Đổi bên", "recirc"),
            IconList.Item(if (open) "Thoát chia đôi" else "Mở chia đôi", if (open) "collapse" else "expand"),
            IconList.Item("Thêm lên màn hình chính", "home"),
            IconList.Item("Thêm vào thanh dưới", "chevron-down"),
            IconList.Item("Xoá cặp này", "close", danger = true),
        )) { i ->
            when (i) {
                0 -> host.pickApp { pkg -> update(s.copy(left = pkg)) }
                1 -> host.pickApp { pkg -> update(s.copy(right = pkg)) }
                2 -> IconList.show(c, "Tỉ lệ trái : phải",
                    Split.PRESETS.map { r -> IconList.Item("$r : ${100 - r}", "dual") }, columns = 2) { r ->
                    update(s.copy(ratio = Split.PRESETS[r]))
                    if (open) host.splitScreen.place(Split.PRESETS[r])
                }
                3 -> update(s.copy(left = s.right, right = s.left, ratio = 100 - s.ratio))
                4 -> host.splitScreen.toggle(s)
                5 -> host.addSplitWidget(s.id)
                6 -> host.addSplitToDock(s)
                7 -> Dialogs.confirm(c, "Xoá cặp chia đôi",
                    "Xoá cặp ${label(s)}? Widget và nút của cặp này sẽ không còn dùng được.", okLabel = "Xoá", danger = true) {
                    if (open) host.splitScreen.close()
                    SplitStore.delete(c, s.id)
                    host.splitsChanged()
                }
            }
        }
    }

    private fun update(s: Split) {
        if (s.left == s.right) return host.toast("Chọn hai app khác nhau")
        SplitStore.put(c, s)
        host.splitsChanged()
    }
}
