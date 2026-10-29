package vn.tht.launcher.system

/** Match an entire package, never a prefix or a different app sharing a stack. */
internal object ExternalLaunchPolicy {
    fun belongs(name: String?, pkg: String): Boolean =
        !name.isNullOrEmpty() && (name == pkg || name.substringBefore('/') == pkg)
    fun mayClearStack(names: List<String?>, pkg: String): Boolean =
        names.isNotEmpty() && names.all { belongs(it, pkg) }
    fun binderAccepted(result: Any?): Boolean = result != false
    /** Android 9 StackInfo carries the type inside Configuration, newer variants expose a getter. */
    fun activityType(info: Any): Int? = runCatching {
        info.javaClass.getMethod("getActivityType").invoke(info) as? Int
    }.getOrNull() ?: runCatching {
        val configuration = info.javaClass.getField("configuration").get(info)
        val window = configuration.javaClass.getField("windowConfiguration").get(configuration)
        window.javaClass.getMethod("getActivityType").invoke(window) as? Int
    }.getOrNull()
}
