package mongojavaimport.sentiments

data class Sentiment(
    val sentence: String,
    val feeling: Feeling,
    val accuracy: Double
)

enum class Feeling {
    Horrible,
    Bad,
    Neutral,
    Good,
    Excellent
}