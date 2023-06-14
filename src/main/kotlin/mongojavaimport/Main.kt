package mongojavaimport

import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader

import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val loader = MongoLoader()
    val targetDirectory = File("C:\\Users\\emili\\Documents\\M2\\big_data\\data_Twitter\\data")
    val files = targetDirectory.listFiles { file -> file.extension == "json" }.toList()
    val index = AtomicInteger(0)

    processFiles(loader, files, index)

    executeSentimentAnalyzer()

    executePythonScriptsInDirectory("src/main/resources/requetes_pipelines")
}

fun processFiles(loader: MongoLoader, files: List<File>, index: AtomicInteger) {
    files.parallelStream().forEach { file ->
        loader.loadJsonIntoDb(file)
        println("File treated: ${index.incrementAndGet()} / ${files.size}")
    }

    println("All files treated.")
}

fun executeSentimentAnalyzer() {
    val process = ProcessBuilder("python", "src/main/resources/sentiment-analyzer.py")
        .redirectErrorStream(true)
        .start()

    val reader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        println(line)
    }

    val exitCode = process.waitFor()
    println("Python sentiment analyzer script execution completed. Exit code: $exitCode")
}

fun executePythonScriptsInDirectory(directoryPath: String) {
    val directory = File(directoryPath)

    if (directory.isDirectory) {
        val pythonScripts = directory.listFiles { file -> file.isFile && file.extension == "py" }

        pythonScripts?.forEach { script ->
            val process = ProcessBuilder("python", script.absolutePath)
                .redirectErrorStream(true)
                .start()

            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                println(line)
            }

            val exitCode = process.waitFor()
            println("Python script execution completed for: ${script.name}. Exit code: $exitCode")
        }
    }
}