package mongojavaimport.sentiments

import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.concurrent.thread
import kotlin.io.path.createTempFile


class SentimentsAnalyzer private constructor(): AutoCloseable {
    companion object {
        fun useSentimentsAnalyzer(fn: (SentimentsAnalyzer) -> Unit) {
            SentimentsAnalyzer().use(fn)
        }
    }

    private val code = this::class.java.getResource("/sentiment-analyzer.py").readText()

    private val codeFile = createTempFile("pythoncode", "py").toFile().apply {
        writeText(code)
    }

    private val parser = JSONParser()

    private val pythonScript = codeFile.absolutePath
    private val pythonPath = "python.exe" // Your Python interpreter path
    private val processBuilder = ProcessBuilder(pythonPath, pythonScript)
    private val process = processBuilder.start()
    private val writer = BufferedWriter(OutputStreamWriter(process.outputStream))
    private val reader = BufferedReader(InputStreamReader(process.inputStream))
    init {
    }

    fun sentimentsOf(sentence: String): Sentiment {
        writer.write(sentence)
        writer.newLine()
        writer.flush()
        val result = (parser.parse(reader.readLine()) as JSONArray)[0] as JSONObject
        val feelingLevel = (result["label"] as String).split(" ")[0].toInt()
        return Sentiment(
            sentence = sentence,
            feeling = Feeling.values()[feelingLevel - 1],
            accuracy = result["score"] as Double
        )
    }

    override fun close() {
        writer.write("<<end of input>>")
        writer.newLine()
        writer.flush()
        writer.close()

        // Output the output of the Python program
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
            println(JSONParser().parse(line))
        }
        process.destroy()
    }
}