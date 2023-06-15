package mongojavaimport

/**
 * Represents the teams mentioned in a tweet.
 */
enum class Equipes {
    CAVALIERS,    // Cleveland Cavaliers
    WIZARDS,      // Washington Wizards
    MAGIC,        // Orlando Magic
    NETS,         // Brooklyn Nets
    ROCKETS,      // Houston Rockets
    PISTONS,      // Detroit Pistons
    KNICKS,       // New York Knicks
    BUCKS,        // Milwaukee Bucks
    CELTICS,      // Boston Celtics
    RAPTORS,      // Toronto Raptors
    GRIZZLIES,    // Memphis Grizzlies
    HORNETS,      // Charlotte Hornets
    BULLS,        // Chicago Bulls
    MAVERICKS,    // Dallas Mavericks
    PELICANS,     // New Orleans Pelicans
    THUNDER,      // Oklahoma City Thunder
    SPURS,        // San Antonio Spurs
    KINGS,        // Sacramento Kings
    NUGGETS,      // Denver Nuggets
    PACERS,       // Indiana Pacers
    SUNS,         // Phoenix Suns
    TRAIL,        // Portland Trail Blazers
    LAKERS,       // Los Angeles Lakers
    HEAT,         // Miami Heat
    WARRIORS,     // Golden State Warriors
    TIMBERWOLVES; // Minnesota Timberwolves

    companion object {
        val anyTeamRegex = Equipes.values().joinToString(separator = "|") { "[\\s|#]+$it[\\s\\.,]" }.toRegex(RegexOption.IGNORE_CASE).toPattern()
    }
}