package mongojavaimport

/**
 * Represents the hashtags related to basketball.
 */
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

    /**
     * Regular expression pattern to match any hashtag related to basketball.
     * This pattern is used to extract relevant hashtags from a tweet.
     */
    companion object {
        val anyHashtagRegex = values().joinToString(separator = "|") { "[#\\s]+$it[\\s\\.,]" }.toRegex(RegexOption.IGNORE_CASE).toPattern()
    }
}