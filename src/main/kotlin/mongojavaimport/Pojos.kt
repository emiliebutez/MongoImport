package mongojavaimport

import org.bson.types.ObjectId
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents a tweet object.
 */
data class Tweet(
    val _id: ObjectId = ObjectId.get(),                   // The unique identifier for the tweet
    val created_at: String,                                // The creation date of the tweet
    val id: Long,                                          // The unique ID of the tweet
    val id_str: String,                                    // The string representation of the tweet ID
    val text: String,                                      // The content of the tweet
    val source: String,                                    // The source of the tweet
    val in_reply_to_status_id: Any?,                       // The ID of the tweet this tweet is replying to, if any
    val in_reply_to_status_id_str: Any?,                   // The string representation of the ID of the tweet this tweet is replying to, if any
    val in_reply_to_user_id: Any?,                         // The ID of the user this tweet is replying to, if any
    val in_reply_to_user_id_str: Any?,                     // The string representation of the ID of the user this tweet is replying to, if any
    val user: FilteredUser,                                // The user who posted the tweet
    val retweeted_status: FilteredRetweetedStatus? = null, // The retweeted status, if this tweet is a retweet
    val is_quote_status: Boolean,                          // Indicates if the tweet is a quote status
    val quote_count: Int,                                  // The number of times this tweet has been quoted
    val reply_count: Int,                                  // The number of replies to this tweet
    val retweet_count: Int,                                // The number of retweets for this tweet
    val favorite_count: Int,                               // The number of times this tweet has been favorited
    val entities: Entities,                                // The entities (hashtags, URLs, mentions, etc.) present in the tweet
    val lang: String? = null,                              // The language of the tweet, if available
    val hour: String? = getHour(created_at),               // The hour of the tweet's creation time
    val date: String? = getDate(created_at),               // The date of the tweet's creation time
    val sentiment: String? = null,                         // The sentiment associated with the tweet
    val equipes: List<String>? = getEquipes(text),          // The list of teams mentioned in the tweet, if any

)

/**
 * Represents a filtered user object.
 */
data class FilteredUser(
    val id: Long,                                          // The unique ID of the user
    val id_str: String,                                    // The string representation of the user ID
    val name: String,                                      // The name of the user
    val screen_name: String,                               // The screen name/handle of the user
    val url: String? = null,                               // The URL associated with the user, if available
    val description: String? = null,                       // The description of the user, if available
    val verified: Boolean,                                 // Indicates if the user is a verified account
    val followers_count: Int,                              // The number of followers the user has
    val friends_count: Int,                                // The number of users the user is following
    val listed_count: Int,                                 // The number of lists the user is a member of
    val favourites_count: Int,                             // The number of tweets the user has favorited
    val statuses_count: Int,                               // The number of tweets/statuses the user has posted
    val created_at: String,                                // The creation date of the user's account
    val lang: Any?,                                        // The language preference of the user, if available
    val date: String? = getDate(created_at),               // The date of the user's account creation
)

/**
 * Represents a filtered retweeted status object.
 */
data class FilteredRetweetedStatus(
    val created_at: String,                                // The creation date of the retweeted status
    val id: Long,                                          // The unique ID of the retweeted status
    val id_str: String,                                    // The string representation of the retweeted status ID
    val text: String,                                      // The content of the retweeted status
    val display_text_range: List<Int>? = null,             // The range of the display text, if available
    val source: String,                                    // The source of the retweeted status
    val in_reply_to_status_id: Any?,                       // The ID of the tweet this retweeted status is replying to, if any
    val in_reply_to_status_id_str: Any?,                   // The string representation of the ID of the tweet this retweeted status is replying to, if any
    val in_reply_to_user_id: Any?,                         // The ID of the user this retweeted status is replying to, if any
    val in_reply_to_user_id_str: Any?,                     // The string representation of the ID of the user this retweeted status is replying to, if any
    val user: FilteredUser,                                // The user who posted the retweeted status
    val is_quote_status: Boolean,                          // Indicates if the retweeted status is a quote status
    val quote_count: Int,                                  // The number of times the retweeted status has been quoted
    val reply_count: Int,                                  // The number of replies to the retweeted status
    val retweet_count: Int,                                // The number of retweets for the retweeted status
    val favorite_count: Int,                               // The number of times the retweeted status has been favorited
    val entities: Entities,                                // The entities (hashtags, URLs, mentions, etc.) present in the retweeted status
    val favorited: Boolean,                                // Indicates if the retweeted status has been favorited
    val retweeted: Boolean,                                // Indicates if the retweeted status has been retweeted
    val possibly_sensitive: Boolean,                       // Indicates if the retweeted status may contain sensitive content
    val filter_level: String,                              // The filter level applied to the retweeted status
    val lang: String,                                      // The language of the retweeted status
    val hour: String? = getHour(created_at),               // The hour of the retweeted status' creation time
    val date: String? = getDate(created_at),               // The date of the retweeted status' creation time
)

/**
 * Represents a entities object.
 */
data class Entities(
    val hashtags: List<Hashtag>,                           // The list of hashtags present in the tweet
    val urls: List<Any>,                                   // The list of URLs present in the tweet
    val user_mentions: List<UserMention>,                   // The list of user mentions present in the tweet
    val symbols: List<Any>,                                // The list of symbols present in the tweet
    val media: List<Media> = emptyList(),                   // The list of media (images, videos, etc.) present in the tweet
)

/**
 * Represents a hashtag object.
 */
data class Hashtag(
    val indices: List<Int>,                                // The character indices where the hashtag appears in the tweet
    val text: String                                       // The text of the hashtag
)

/**
 * Represents a user mention object.
 */
data class UserMention(
    val screen_name: String? = null,                       // The screen name/handle of the mentioned user
    val name: String? = null,                              // The name of the mentioned user
    val id: Long? = null,                                  // The unique ID of the mentioned user
    val id_str: String? = null,                            // The string representation of the mentioned user ID
    val indices: List<Int>? = null,                        // The character indices where the user mention appears in the tweet
)

/**
 * Represents a media object.
 */
data class Media(
    val id: Long,                                          // The unique ID of the media
    val id_str: String,                                    // The string representation of the media ID
    val indices: List<Int>,                                // The character indices where the media appears in the tweet
    val media_url: String,                                 // The URL of the media
    val media_url_https: String,                           // The HTTPS URL of the media
    val url: String,                                       // The displayed URL of the media
    val display_url: String,                               // The displayed URL of the media with formatting
    val expanded_url: String,                              // The expanded version of the displayed URL
    val type: String,                                      // The type of the media (e.g., photo, video)
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
