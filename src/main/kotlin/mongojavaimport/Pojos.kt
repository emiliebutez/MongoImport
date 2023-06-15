package mongojavaimport

import org.bson.types.ObjectId
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents a tweet object.
 */
data class Tweet(
    val _id: ObjectId = ObjectId.get(),
    val created_at: String,
    val id: Long,
    val id_str: String,
    val text: String,
    val source: String,
    val in_reply_to_status_id: Any?,
    val in_reply_to_status_id_str: Any?,
    val in_reply_to_user_id: Any?,
    val in_reply_to_user_id_str: Any?,
    val user: FilteredUser,
    val retweeted_status: FilteredRetweetedStatus? = null,
    val is_quote_status: Boolean,
    val quote_count: Int,
    val reply_count: Int,
    val retweet_count: Int,
    val favorite_count: Int,
    val entities: Entities,
    val lang: String? = null,
    val hour: String? = getHour(created_at),
    val date: String? = getDate(created_at),
    val sentiment: String? = null,
    val equipes: List<String>? = getEquipes(text),
)

/**
 * Represents a filtered user object.
 */
data class FilteredUser(
    val id: Long,
    val id_str: String,
    val name: String,
    val screen_name: String,
    val url: String? = null,
    val description: String? = null,
    val verified: Boolean,
    val followers_count: Int,
    val friends_count: Int,
    val listed_count: Int,
    val favourites_count: Int,
    val statuses_count: Int,
    val created_at: String,
    val lang: Any?,
    val date: String? = getDate(created_at),
)

/**
 * Represents a filtered retweeted status object.
 */
data class FilteredRetweetedStatus(
    val created_at: String,
    val id: Long,
    val id_str: String,
    val text: String,
    val display_text_range: List<Int>? = null,
    val source: String,
    val in_reply_to_status_id: Any?,
    val in_reply_to_status_id_str: Any?,
    val in_reply_to_user_id: Any?,
    val in_reply_to_user_id_str: Any?,
    val user: FilteredUser,
    val is_quote_status: Boolean,
    val quote_count: Int,
    val reply_count: Int,
    val retweet_count: Int,
    val favorite_count: Int,
    val entities: Entities,
    val favorited: Boolean,
    val retweeted: Boolean,
    val possibly_sensitive: Boolean,
    val filter_level: String,
    val lang: String,
    val hour: String? = getHour(created_at),
    val date: String? = getDate(created_at),
)

/**
 * Represents a entities object.
 */
data class Entities(
    val hashtags: List<Hashtag>,
    val urls: List<Any>,
    val user_mentions: List<UserMention>,
    val symbols: List<Any>,
    val media: List<Media> = emptyList(),
)

/**
 * Represents a hashtag object.
 */
data class Hashtag(
    val indices: List<Int>,
    val text: String
)

/**
 * Represents a user mention object.
 */
data class UserMention(
    val screen_name: String? = null,
    val name: String? = null,
    val id: Long? = null,
    val id_str: String? = null,
    val indices: List<Int>? = null,
)

/**
 * Represents a media object.
 */
data class Media(
    val id: Long,
    val id_str: String,
    val indices: List<Int>,
    val media_url: String,
    val media_url_https: String,
    val url: String,
    val display_url: String,
    val expanded_url: String,
    val type: String,
)

/**
 * Retrieves the hour from a given date string.
 *
 * @param date The date string to extract the hour from
 * @return The hour in HH:mm format
 */
fun getHour(date: String) : String {
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(date)
    val hourFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return hourFormat.format(date)
}

/**
 * Retrieves the date from a given date string.
 *
 * @param date The date string to extract the date from
 * @return The date in dd/MM/yyyy format
 */
fun getDate(date: String) : String {
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(date)
    val dayFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return dayFormat.format(date)
}

/**
 * Retrieves the list of teams mentioned in a tweet.
 *
 * @param textTweet The content of the tweet
 * @return The list of teams mentioned in the tweet
 */
fun getEquipes(textTweet: String): List<String> {
    val equipes = Equipes.values().map { it.name }
    return equipes.filter { textTweet.contains(it, ignoreCase = true) }
}
