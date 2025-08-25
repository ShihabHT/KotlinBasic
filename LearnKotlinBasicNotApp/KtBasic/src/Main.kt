//Main tutorial link
// https://youtu.be/F9UC9DY-vIU?si=i2HV_KrOqLyoaOl-

fun main() {
    // declaration of variable--
    //declaration variableNAME: TYPE = "VALUE"
    var name2: String = "windows"

    val name = "Kotlin" //immutable "val"
    var mName = "Android" //mutable "var"
    var arr = arrayOf("1","2")
    arr.forEach { a ->
        println(a)
    }
    arr.forEachIndexed { index, string ->
        println("$string is at $index")
    }
    var list = mutableListOf("1", "2")

    var map = mapOf(1 to "a", 2 to "b")
    println(map[1])
    var lst: Array<String> = arrayOf("")
    yo(*lst)

    //Class
    var person = Person("yo", "boy")
    person.firstName
}
fun yo(vararg strs: String){
    strs.forEach {
        println(it)
    }
}
