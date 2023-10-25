package com.example.testxmlui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CViewModelActivityCompose : ViewModel() {
    var menuState by mutableStateOf(false)
        private set
    var value1 by mutableStateOf("")
        private set
    var value2 by mutableStateOf("")
        private set

    fun updateMenuState(state : Boolean)
    {
        menuState = state
    }
    fun updateValue1(val1 : String)
    {
        value1 = val1
    }
    fun updateValue2(val2 : String)
    {
        value2 = val2
    }
}