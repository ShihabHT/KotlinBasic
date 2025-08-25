fun main() {
    val list = listOf("Kotlin", "Java", "Javascript", "C++", null)
    list
        .filterNotNull() // keeps not null values only
        .filter { it
            it.startsWith("J")
        }
        .map { it
            it.length // map converts the type in this case String - Int
        }
        .forEach { it
        println(it)
    }
}


/*
there are many kotlin standard library functions. watch the last part of
the video to learn more
 */