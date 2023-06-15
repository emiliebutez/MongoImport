package mongojavaimport

import org.bson.types.ObjectId
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents a tweet object.
 *
 * @property _id The unique identifier for the tweet.
 * @property created_at The creation date of the tweet.
 * @property id The unique ID of the tweet.
 * @property id_str The string representation of the tweet ID.
 * @property text The content of the tweet.
 * @property source The source of the tweet.
 * @property in_reply_to_status_id The ID of the tweet this tweet is replying to, if any.
 * @property in_reply_to_status_id_str The string representation of the ID of the tweet this tweet is replying to, if any.
 * @property in_reply_to_user_id The ID of the user this tweet is replying to, if any.
 * @property in_reply_to_user_id_str The string representation of the ID of the user this tweet is replying to, if any.
 * @property user The user who posted the tweet.
 * @property retweeted_status The retweeted status, if this tweet is a retweet.
 * @property is_quote_status Indicates if the tweet is a quote status.
 * @property quote_count The number of times this tweet has been quoted.
 * @property reply_count The number of replies to this tweet.
 * @property retweet_count The number of retweets for this tweet.
 * @property favorite_count The number of times this tweet has been favorited.
 * @property entities The entities (hashtags, URLs, mentions, etc.) present in the tweet.
 * @property lang The language of the tweet, if available.
 * @property hour The hour of the tweet's creation time.
 * @property date The date of the tweet's creation time.
 * @property sentiment The sentiment associated with the tweet.
 * @property equipes The list of teams mentioned in the tweet, if any.
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
    val equipes: List<String>? = getEquipes(text)
)

/**
 * Represents a filtered user object.
 *
 * @property id The unique ID of the user.
 * @property id_str The string representation of the user ID.
 * @property name The name of the user.
 * @property screen_name The screen name/handle of the user.
 * @property url The URL associated with the user, if available.
 * @property description The description of the user, if available.
 * @property verified Indicates if the user is a verified account.
 * @property followers_count The number of followers the user has.
 * @property friends_count The number of users the user is following.
 * @property listed_count The number of lists the user is a member of.
 * @property favourites_count The number of tweets the user has favorited.
 * @property statuses_count The number of tweets/statuses the user has posted.
 * @property created_at The creation date of the user's account.
 * @property lang The language preference of the user, if available.
 * @property date The date of the user's account creation.
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
    val date: String? = getDate(created_at)
)

/**
 * Represents a filtered retweeted status object.
 *
 * @property created_at The creation date of the retweeted status.
 * @property id The unique ID of the retweeted status.
 * @property id_str The string representation of the retweeted status ID.
 * @property text The content of the retweeted status.
 * @property display_text_range The range of the display text, if available.
 * @property source The source of the retweeted status.
 * @property in_reply_to_status_id The ID of the tweet this retweeted status is replying to, if any.
 * @property in_reply_to_status_id_str The string representation of the ID of the tweet this retweeted status is replying to, if any.
 * @property in_reply_to_user_id The ID of the user this retweeted status is replying to, if any.
 * @property in_reply_to_user_id_str The string representation of the ID of the user this retweeted status is replying to, if any.
 * @property user The user who posted the retweeted status.
 * @property is_quote_status Indicates if the retweeted status is a quote status.
 * @property quote_count The number of times the retweeted status has been quoted.
 * @property reply_count The number of replies to the retweeted status.
 * @property retweet_count The number of retweets for the retweeted status.
 * @property favorite_count The number of times the retweeted status has been favorited.
 * @property entities The entities (hashtags, URLs, mentions, etc.) present in the retweeted status.
 * @property favorited Indicates if the retweeted status has been favorited.
 * @property retweeted Indicates if the retweeted status has been retweeted.
 * @property possibly_sensitive Indicates if the retweeted status may contain sensitive content.
 * @property filter_level The filter level applied to the retweeted status.
 * @property lang The language of the retweeted status.
 * @property hour The hour of the retweeted status' creation time.
 * @property date The date of the retweeted status' creation time.
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
    val date: String? = getDate(created_at)
)

/**
 * Represents an entities object.
 *
 * @property hashtags The list of hashtags present in the tweet.
 * @property urls The list of URLs present in the tweet.
 * @property user_mentions The list of user mentions present in the tweet.
 * @property symbols The list of symbols present in the tweet.
 * @property media The list of media (images, videos, etc.) present in the tweet.
 */
data class Entities(
    val hashtags: List<Hashtag>,
    val urls: List<Any>,
    val user_mentions: List<UserMention>,
    val symbols: List<Any>,
    val media: List<Media> = emptyList()
)

/**
 * Represents a hashtag object.
 *
 * @property indices The character indices where the hashtag appears in the tweet.
 * @property text The text of the hashtag.
 */
data class Hashtag(
    val indices: List<Int>,
    val text: String
)

/**
 * Represents a user mention object.
 *
 * @property screen_name The screen name/handle of the mentioned user.
 * @property name The name of the mentioned user.
 * @property id The unique ID of the mentioned user.
 * @property id_str The string representation of the mentioned user ID.
 * @property indices The character indices where the user mention appears in the tweet.
 */
data class UserMention(
    val screen_name: String? = null,
    val name: String? = null,
    val id: Long? = null,
    val id_str: String? = null,
    val indices: List<Int>? = null
)

/**
 * Represents a media object.
 *
 * @property id The unique ID of the media.
 * @property id_str The string representation of the media ID.
 * @property indices The character indices where the media appears in the tweet.
 * @property media_url The URL of the media.
 * @property media_url_https The HTTPS URL of the media.
 * @property url The displayed URL of the media.
 * @property display_url The displayed URL of the media with formatting.
 * @property expanded_url The expanded version of the displayed URL.
 * @property type The type of the media (e.g., photo, video).
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
    val type: String
)

/**
 * Retrieves the hour from a given date string.
 *
 * @param date The date string to extract the hour from.
 * @return The hour in HH:mm format.
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
 * @param date The date string to extract the date from.
 * @return The date in dd/MM/yyyy format.
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
 * @param textTweet The content of the tweet.
 * @return The list of teams mentioned in the tweet.
 */
fun getEquipes(textTweet: String): List<String> {
    val equipes = Equipes.values().map { it.name }
    return equipes.filter { textTweet.contains(it, ignoreCase = true) }
}
