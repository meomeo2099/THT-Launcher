package vn.tht.launcher.system

/** Match an entire package, never a prefix or a different app sharing a stack. */
internal object ExternalLaunchPolicy {
    fun belongs(name: String?, pkg: String): Boolean =
        !name.isNullOrEmpty() && (name == pkg || name.substringBefore('/') == pkg)
    fun mayClearStack(names: List<String?>, pkg: String): Boolean =
        names.isNotEmpty() && names.all { belongs(it, pkg) }
    fun binderAccepted(result: Any?): Boolean = result != false
}
