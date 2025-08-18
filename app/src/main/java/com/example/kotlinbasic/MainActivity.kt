package com.example.kotlinbasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinbasic.ui.theme.KotlinBasicTheme

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
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    KotlinBasicTheme {
//        Greeting("Android")
//    }
//}