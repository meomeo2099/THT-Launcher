package vn.tht.launcher.system

import android.content.Context
import vn.tht.launcher.settings.Prefs

/** Explicit OEM handoff: persist through process restart, resume only on return to THT. */
object StockMode {
    fun active(c: Context) = Prefs(c).sp.getBoolean("stock_mode", false)
    fun enter(c: Context) {
        Prefs(c).sp.edit().putBoolean("stock_mode", true).commit()
        ExternalAppLauncher.cancelRepairs()
        AppWindowPolicy.restore(c)
        Overscan.set(c, Prefs(c), 0)
        NavAssistService.stockModeChanged()
        vn.tht.launcher.shortcuts.FloatingDock.stockModeChanged(c)
    }
    fun exit(c: Context) {
        if (!active(c)) return
        Prefs(c).sp.edit().putBoolean("stock_mode", false).commit()
        NavAssistService.stockModeChanged()
        vn.tht.launcher.shortcuts.FloatingDock.stockModeChanged(c)
    }
}
