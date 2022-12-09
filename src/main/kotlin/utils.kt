fun readResourceFile(name: String) = object {}::class.java.classLoader.getResource(name)!!.readText()

fun readResourceFileAsLines(name: String): List<String> = readResourceFile(name).lines()