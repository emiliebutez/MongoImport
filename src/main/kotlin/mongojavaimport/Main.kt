package mongojavaimport

import mongojavaimport.sentiments.SentimentsAnalyzer
import mongojavaimport.sentiments.SentimentsAnalyzer.Companion.useSentimentsAnalyzer
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val loader = MongoLoader()
    val targetDirectory = File("C:\\Users\\emili\\Documents\\M2\\big_data\\data_Twitter\\data")
    val files = targetDirectory.listFiles { file -> file.extension == "json" }.toList()
    val index = AtomicInteger(0)

    useSentimentsAnalyzer { analyzer ->
        files.parallelStream().forEach { file ->
            loader.loadJsonIntoDb(file, sentimentsAnalyzer = analyzer)
            println("File treated: ${index.incrementAndGet()} / ${files.size}")
        }
    }

    println("ok")
}