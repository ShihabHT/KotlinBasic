package com.example.kotlinbasic
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello  $name!",
        modifier = modifier
    )
}

Q1. explanation of above code snippet

A1.
That code defines a reusable UI component in Jetpack Compose, Android's modern UI toolkit. In simple terms, it's a blueprint for a piece of text on the screen that says "Hi" followed by a name you provide.
Let's break down each part of the code snippet:

@Composable
This is an annotation that tells the Compose compiler that this function is intended to create UI. It transforms data into a UI element. Think of it as a special label that marks this function as a building block for your app's screen. All Composable functions must be called from other Composable functions.

fun Greeting(name: String, modifier: Modifier = Modifier)
This is the function declaration.

fun Greeting: Defines a function named Greeting. By convention, Composable function names are capitalized.

name: String: This is a parameter. It means the Greeting function must be given a piece of text (a String), like "Android" or "John", which it will use to display the message.
modifier: Modifier = Modifier: This is an optional parameter. A Modifier is used to decorate or add behavior to a Composable, such as changing its size, adding padding, handling user input, or setting a background color. Giving it a default value of Modifier makes it optional to use.

Text(...)
This is another Composable function, provided by the Compose framework, that is used to display text on the screen.

text = "Hi $name!": This sets the actual text to be displayed. The $name is a string template that inserts the value of the name parameter into the text. So if you pass "Android" as the name, the text will be "Hi Android!".
modifier = modifier: This applies the modifier passed into the Greeting function to the Text composable. This allows you to customize the Text element from the outside when you call Greeting.

How It's Used
You would call this Greeting function from another Composable function to actually display it on the screen.


For example:

@Composable
fun MyApp() {
    // This will display "Hi Android!" with 16dp of padding around it.
    Greeting(
        name = "Android",
        modifier = Modifier.padding(16.dp)
    )
}
In this example, MyApp calls Greeting, passing in the name "Android" and a Modifier that adds padding. The Greeting function then uses these values to configure and display the Text element.





Q2. can i achieve the increment of count by declaring a function out side mainActivity and and call the function when the button is pressed. for example onClick = increase()

A2. Of course! Yes, you can absolutely achieve that. Moving logic into separate functions is a great practice for keeping your code clean and reusable.
However, your proposed syntax onClick = increase() needs a small but important correction. You need to pass a lambda (a block of code to execute later) to onClick.

Here's the right way to do it and why.
You need to do two things:
    -Define a function that takes the state object as a parameter.
    -Call that function from inside a lambda {} in the Button's onClick.

1. Define the Function
You can place this function outside your MainActivity class, but within the same file. It needs to accept a MutableState<Int> so it knows which variable to change.

    fun increaseCount(count: MutableState<Int>) {
        count.value++
    }
2. Call the Function in the Lambda
Inside your Button, the onClick parameter expects a function to be executed when the user taps it. You provide this by wrapping your function call in a lambda {}.

    Button(onClick = { increaseCount(count) }) { // Correct way
        Text("Hit")
    }


Why onClick = increase() is Incorrect
Let's break down the difference:
onClick = { increaseCount(count) } ✅
This gives the button a recipe (the lambda) of what to do when it's clicked. When the user taps the button, the code inside the {} runs, which then calls increaseCount().

onClick = increaseCount(count) ❌
This immediately runs the increaseCount(count) function during composition (when the UI is being built) and tries to assign its return value to onClick. Since increaseCount doesn't return anything (Unit), this will cause an error. It's like eating the cake instead of handing over the recipe.


Complete Code Example
Here is what your MainActivity.kt file would look like with this change:

// Function is defined outside the class
fun increaseCount(count: MutableState<Int>) {
    count.value++
    // You could add more logic here, like printing to the console
    println("The count is now: ${count.value}")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBasicTheme {
                MyClickerApp()
            }
        }
    }
}

@Composable
fun MyClickerApp() {
    var count = remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = count.value.toString())
        Button(onClick = { increaseCount(count) }) { // Calling the external function
            Text("Hit")
        }
    }
}

*/