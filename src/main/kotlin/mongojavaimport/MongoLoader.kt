package mongojavaimport

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import lk.chathurabuddi.json.schema.filter.JsonSchemaFilter
import java.io.File
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document
import com.mongodb.client.model.InsertOneModel;

class MongoLoader {
    private val objectMapper = ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerKotlinModule()
    private val client = MongoClients.create("mongodb://localhost:27017")
    val database = client.getDatabase("tweetsFilteredV2");
    val coll = database.getCollection("tweetsFilteredV2");

    fun filterProperties(json: String): Tweet {
        return objectMapper.readValue(json, Tweet::class.java)
    }

    fun loadJsonIntoDb(file: File) {
        val hashtagNames = Hashtags.values().map { it.name }
        val content = file.readLines()
            .parallelStream()
            .filter { it.isNotEmpty() }
            .map { filterProperties(it) }
            .filter {
                //it.entities.hashtags.any { h -> h.toString().uppercase() in hashtagNames }
                it.text.contains("NBA")
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