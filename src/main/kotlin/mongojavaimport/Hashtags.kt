package mongojavaimport

enum class Hashtags {
    NBA,
    BASKETBALL,
    LAKERS,
    BULLS,
    MAVERICKS,
    TIMBERWOLVES,
    TRUETOATLANTA,
    BLEEDGREEN,
    NETSWORLD,
    BULLSNATION,
    BETHEFIGHT,
    MILEHIGHBASKETBALL,
    DUBNATION,
    LAKESHOW,
    GRINDCITY,
    HEATCULTURE,
    FEARTHEDEER;

    companion object {
        val anyHashtagRegex = values().joinToString(separator = "|") { "[#\\s]+$it[\\s\\.,]" }.toRegex(RegexOption.IGNORE_CASE).toPattern()
    }
}