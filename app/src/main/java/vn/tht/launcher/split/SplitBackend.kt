package vn.tht.launcher.split

/** Coolray advertises secondary displays but can reject individual activities at launch. */
internal object SplitBackend {
    fun hosted(preference: String, capable: Boolean, coolray: Boolean): Boolean =
        capable && (preference == "hosted" || preference == "auto" && !coolray)
}
