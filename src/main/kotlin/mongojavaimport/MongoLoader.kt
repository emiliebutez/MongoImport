package mongojavaimport

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import com.mongodb.client.MongoClients;
import org.bson.Document
import com.mongodb.client.model.InsertOneModel;

/**
 * The `MongoLoader` class is responsible for loading JSON data into a MongoDB database.
 */
class MongoLoader {
    private val objectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerKotlinModule()
    private val client = MongoClients.create("mongodb://localhost:27017")
    val database = client.getDatabase("tweetsDL");
    val coll = database.getCollection("tweetsDL");

    /**
     * Filters the relevant properties from the JSON and deserializes it into a `Tweet` object.
     *
     * @param json The JSON string to be filtered and deserialized.
     * @return The deserialized `Tweet` object.
     */
    fun filterProperties(json: String): Tweet {
        return objectMapper.readValue(json, Tweet::class.java)
    }

    /**
     * Loads the JSON data from a file into the MongoDB database.
     * Only tweets containing relevant hashtags are inserted into the database.
     *
     * @param file The file containing the JSON data.
     */
    fun loadJsonIntoDb(file: File) {
        val content = file.readLines()
            .parallelStream()
            .filter { it.isNotEmpty() }
            .map { filterProperties(it) }
            .filter { tweet ->
                Hashtags.anyHashtagRegex.matcher(tweet.text).find()
            }
            .map { objectMapper.writeValueAsString(it) }
            .map { InsertOneModel(Document.parse(it)) }
            .toList()

        if (content.isNotEmpty()) {
            coll.bulkWrite(content)
            println("Added ${content.size} entries")
        }
    }
}