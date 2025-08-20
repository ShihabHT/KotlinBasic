package com.example.kotlinbasic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var count = mutableStateOf(0)
        private  set
    fun increaseCount(count: MutableState<Int>) {
        count.value++
        println("The count is now: ${count.value}")
    }
}