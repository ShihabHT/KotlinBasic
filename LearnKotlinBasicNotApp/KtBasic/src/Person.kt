class Person (var firstName: String = "peter", val lastName: String = "parker") {
    var nickName = "nick"
        get() {
            println("")
            return field
        }

    fun printInformation(){
        println("$firstName - $lastName - $nickName")
    }
}
private var per = Person("", "fd")