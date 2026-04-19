package com.rodrigo.crashcourse.app.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel(){

    var count by mutableIntStateOf(0)
        private set

    var texts by mutableStateOf(listOf<String>())
        private set
    var text by mutableStateOf("")
        private set

    fun onTextChanged(newText:String){
        text = newText
    }

    fun addText(){
        if (text.isNotBlank()) {
            count++
            texts = texts + text
            text = ""
        }
    }

}