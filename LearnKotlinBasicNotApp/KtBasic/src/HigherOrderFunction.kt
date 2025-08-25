fun filteredStrings(list: List<String>, predicate: (String) -> Boolean){
    list.forEach { it
        if(predicate(it)){
            println(it)
        }
    }
}

// if we want to pass it null as a function
fun filteredStrings_2(list: List<String>, predicate: ((String) -> Boolean)?){
    list.forEach { it
        if(predicate?.invoke(it) == true){
            println(it)
        }
    }
}
// if want to store function in a variable and pass the variable
val predicate: (String) -> Boolean = {
    it.startsWith("J")
}

// function that returns another function
fun getPrintPredicate(): (String)-> Boolean{
    return {it.startsWith("J")}
}

fun main(){
    val list = listOf("Kotlin", "Java", "Javascript", "C++")

    // one way to declare the function with a lambda
    filteredStrings(list, {it.startsWith("J")})

    // another way, if the last parameter of a function is a function then
    // we can specify that as a lambda outside the function body. This is
    // actually similar looking to the forEach function as we used before
    // see the following code
    // When the function is null is declared outside the parenthesis
    println("\n---When function is declared outside the parenthesis---")
    filteredStrings(list){
        it.startsWith("K")
    }

    // When the function is null
    println("\n---When function is null---")
    filteredStrings_2(list, null)

    // function stored in variable
    println("\n---For function stored in variable---")
    filteredStrings_2(list, predicate)

    // using function that returns another function
    println("\n---using function that returns another function---")
    filteredStrings_2(list, getPrintPredicate())
}