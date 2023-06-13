package mongojavaimport

enum class Hashtags {
    NBA,
    BASKETBALL,
    LAKERS,
    BULLS,
    MAVERICKS,
    TIMBERWOLVES;

    companion object {
        val anyHashtagRegex = values().joinToString(separator = "|") { "[#\\s]+$it[\\s\\.,]" }.toRegex(RegexOption.IGNORE_CASE).toPattern()
    }
}