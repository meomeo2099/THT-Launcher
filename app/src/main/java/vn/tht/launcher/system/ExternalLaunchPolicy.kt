package vn.tht.launcher.system

/** Match an entire package, never a prefix or a different app sharing a stack. */
internal object ExternalLaunchPolicy {
    fun belongs(name: String?, pkg: String): Boolean =
        !name.isNullOrEmpty() && (name == pkg || name.substringBefore('/') == pkg)
    fun mayClearStack(names: List<String?>, pkg: String): Boolean =
        names.isNotEmpty() && names.all { belongs(it, pkg) }
    fun binderAccepted(result: Any?): Boolean = result != false
    /** Android 9 StackInfo carries the type inside Configuration, newer variants expose a getter. */
    fun activityType(info: Any): Int? = windowValue(info, "getActivityType")
    fun windowingMode(info: Any): Int? = windowValue(info, "getWindowingMode")
    fun mayRepairFullscreen(mode: Int?): Boolean = mode == 1 || mode == 5
    /** A fullscreen standard stack shares one display-sized frame, even when it holds other tasks.
     * Freeform stacks may intentionally be small, so they still require exclusive ownership. */
    fun mayClearFullscreenStack(standard: Boolean, mode: Int?, top: String?, names: List<String?>, pkg: String): Boolean =
        standard && top == pkg && (mode == 1 || mode == 5 && mayClearStack(names, pkg))
    private fun windowValue(info: Any, getter: String): Int? = runCatching {
        info.javaClass.getMethod(getter).invoke(info) as? Int
    }.getOrNull() ?: runCatching {
        val configuration = info.javaClass.getField("configuration").get(info)
        val window = configuration.javaClass.getField("windowConfiguration").get(configuration)
        window.javaClass.getMethod(getter).invoke(window) as? Int
    }.getOrNull()
}
