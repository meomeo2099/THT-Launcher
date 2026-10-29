package vn.tht.launcher.system

/** Add only selected packages; preserve other clauses and explicit exclusions. */
internal object ImmersivePolicy {
    fun scoped(base: String?, packages: Set<String>): String? {
        val names = packages.filter { it.matches(Regex("[A-Za-z0-9_]+(\\.[A-Za-z0-9_]+)+")) }.sorted()
        if (names.isEmpty()) return base
        val clauses = base.orEmpty().split(':').filter { it.isNotBlank() }.toMutableList()
        val full = clauses.withIndex().filter { it.value.startsWith("immersive.full=") }
        if (full.size > 1) return null
        for (clause in clauses.filter { it.startsWith("immersive.") }) {
            val tokens = clause.substringAfter('=', "").split(',')
            if (tokens.any { it == "-*" || it == "-apps" || it.removePrefix("-") in names && it.startsWith('-') }) return null
        }
        val index = full.firstOrNull()?.index
        val existing = index?.let { clauses[it].substringAfter('=').split(',').filter(String::isNotBlank) }.orEmpty()
        if ("*" in existing || "apps" in existing) return base
        val value = "immersive.full=" + (existing + names).distinct().joinToString(",")
        if (index == null) clauses += value else clauses[index] = value
        return clauses.joinToString(":")
    }
}
