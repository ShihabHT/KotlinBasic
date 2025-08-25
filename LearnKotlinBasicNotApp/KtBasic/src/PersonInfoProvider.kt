interface PersonInfoProvider {
    fun printInfo(person: Person){
        person.printInformation()
        println("In Person Info Provider")
    }
    var x: String
}

open class BasicInfoProvider: PersonInfoProvider{
    override fun printInfo(person: Person) {
        person.printInformation()
        println("In Basic Info Provider")
    }

    override var x: String
        get() = TODO("Not yet implemented")
        set(value) {}

}
fun BasicInfoProvider.printExtension(){
    println("Extension name of class ${BasicInfoProvider::class.simpleName}")
}
var BasicInfoProvider.y: String
    get() = "extension"
    set(value) = TODO()

fun main() {
    val provider = BasicInfoProvider()
    val per = Person()
    provider.printInfo(per)
    var pro = object : PersonInfoProvider{
        override var x: String
            get() = TODO("Not yet implemented")
            set(value) {}
    }
    var protest = object : BasicInfoProvider(){

    }
    protest.printExtension()
    println(protest.y)
}




/*
An interface in Kotlin defines a contract for classes to adhere to. It outlines a set of abstract methods and properties that implementing classes must provide concrete implementations for.

Key characteristics of interfaces in Kotlin:
    Abstract Methods and Properties:
        Interfaces can declare abstract methods (without a body) and abstract properties, which require implementing classes to provide their definitions.
    Default Implementations:
        Unlike traditional interfaces in some other languages, Kotlin interfaces can also include default implementations for methods. If an implementing class doesn't override a method with a default implementation, the interface's default implementation will be used.
    No State:
        Interfaces cannot store state; they cannot have constructors or backing fields for properties. Properties declared in an interface must either be abstract or provide accessor implementations.
    Multiple Inheritance of Behavior:
        A class can implement multiple interfaces, allowing it to inherit and combine behaviors from different sources, which is a form of multiple inheritance of implementation.
    Defining Capabilities:
        Interfaces are commonly used to define a set of capabilities or behaviors that can be shared across various, potentially unrelated, classes. This promotes code reusability and a clear separation of concerns.
    Functional (SAM) Interfaces:
        An interface with only one abstract method can be declared as a functional interface using the fun modifier. This enables the use of SAM (Single Abstract Method) conversions, allowing you to pass lambda expressions as implementations, making your code more concise.

Example:

interface Vehicle {
    val speed: Int // Abstract property
    fun accelerate() // Abstract method
    fun brake() { // Method with default implementation
        println("Applying brakes.")
    }
}

class Car : Vehicle {
    override val speed: Int = 100
    override fun accelerate() {
        println("Car is accelerating.")
    }
    // No need to override brake() if the default implementation is sufficient
}
 */