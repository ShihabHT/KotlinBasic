package com.example.kotlinbasic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var count = mutableStateOf(0)
        private  set  //ensures 'count' can only be written from this file and read from other
    fun increaseCount() {
        count.value++
        println("The count is now: ${count.value}")
    }
}

/*
the view model class is designed to store and manage UI related data in a lifecycle conscious
way. The view model class allows data to survive configuration changes such as a screen
rotation. So when we are talking about activity or a fragment, we are actually referring to
UI controllers. And view model class should be created for each UI controller in order to
separate things like data and different calculations from a UI controller. In a UI controller
there should only be necessary code for managing our UI and everything else could be placed
inside the view model class.
 */