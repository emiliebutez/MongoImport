package mongojavaimport

import org.bson.types.ObjectId
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

//import com.fasterxml.jackson.annotation.JsonInclude
//import com.fasterxml.jackson.annotation.JsonProperty
//import com.fasterxml.jackson.annotation.JsonPropertyOrder
//import org.bson.types.ObjectId
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//data class Tweet(
//    @JsonProperty("_id")
//    val id: ObjectId = ObjectId.get(),
//    @JsonProperty("id")
//    val tweetId: Long,
//    @JsonProperty("created_at")
//    val createdAt: String,
//    @JsonProperty("id_str")
//    val idStr: String,
//    @JsonProperty("text")
//    val text: String,
//    @JsonProperty("source")
//    val source: String,
//    @JsonProperty("in_reply_to_status_id")
//    val inReplyToStatusId: String? = null,
//    @JsonProperty("in_reply_to_status_id_str")
//    val inReplyToStatusIdStr: String? = null,
//    @JsonProperty("in_reply_to_user_id")
//    val inReplyToUserId: String? = null,
//    @JsonProperty("in_reply_to_user_id_str")
//    val inReplyToUserIdStr: String? = null,
//    @JsonProperty("is_quote_status")
//    val isQuoteStatus: Boolean,
//    @JsonProperty("quote_count")
//    val quoteCount: Int,
//    @JsonProperty("reply_count")
//    val replyCount: Int,
//    @JsonProperty("retweet_count")
//    val retweetCount: Int,
//    @JsonProperty("favorite_count")
//    val favoriteCount: Int,
//    @JsonProperty("favorited")
//    val favorited: Boolean,
//    @JsonProperty("retweeted")
//    val retweeted: Boolean,
//    @JsonProperty("possibly_sensitive")
//    val possiblySensitive: Boolean,
//    @JsonProperty("filter_level")
//    val filterLevel: String,
//    @JsonProperty("lang")
//    val lang: String,
//    @JsonProperty("timestamp_ms")
//    val timestampMs: String
//)


data class Tweet(
    val _id: ObjectId = ObjectId.get(),
    val created_at: String,
    val id: Long,
    val id_str: String,
    val text: String,
    val source: String,
    val in_reply_to_status_id: Any?, // null in the provided data
    val in_reply_to_status_id_str: Any?, // null in the provided data
    val in_reply_to_user_id: Any?, // null in the provided data
    val in_reply_to_user_id_str: Any?, // null in the provided data
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
    val date: String? = null,
    val sentiment: Sentiments? = Sentiments.NEUTRE
)

fun getHour(date: String) : String {
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(date)
    val hourFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return hourFormat.format(date)
}

fun getDate(date: String) : String {
    val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH)
    val date = dateFormat.parse(date)
    val dayFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return dayFormat.format(date)
}

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
    val lang: Any?, // null in the provided data
)

data class FilteredRetweetedStatus(
    val created_at: String,
    val id: Long,
    val id_str: String,
    val text: String,
    val display_text_range: List<Int>? = null,
    val source: String,
    val in_reply_to_status_id: Any?, // null in the provided data
    val in_reply_to_status_id_str: Any?, // null in the provided data
    val in_reply_to_user_id: Any?, // null in the provided data
    val in_reply_to_user_id_str: Any?, // null in the provided data
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
)

data class Entities(
    val hashtags: List<Hashtag>, // Empty list in the provided data
    val urls: List<Any>, // Empty list in the provided data
    val user_mentions: List<UserMention>,
    val symbols: List<Any>, // Empty list in the provided data
    val media: List<Media> = emptyList(),
)

//
data class Hashtag(
    val indices: List<Int>,
    val text: String
)

data class UserMention(
    val screen_name: String? = null,
    val name: String? = null,
    val id: Long? = null,
    val id_str: String? = null,
    val indices: List<Int>? = null,
)

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
