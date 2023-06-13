package mongojavaimport

enum class Equipes {
    CAVALIERS,
    WIZARDS,
    MAGIC,
    NETS,
    ROCKETS,
    PISTONS,
    KNICKS,
    BUCKS,
    CELTICS,
    RAPTORS,
    GRIZZLIES,
    HORNETS,
    BULLS,
    MAVERICKS,
    PELICANS,
    THUNDER,
    SPURS,
    KINGS,
    NUGGETS,
    PACERS,
    SUNS,
    TRAIL,
    LAKERS,
    HEAT,
    WARRIORS,
    TIMBERWOLVES;

    companion object {
        val anyTeamRegex = Equipes.values().joinToString(separator = "|") { "[\\s|#]+$it[\\s\\.,]" }.toRegex(RegexOption.IGNORE_CASE).toPattern()
    }
}