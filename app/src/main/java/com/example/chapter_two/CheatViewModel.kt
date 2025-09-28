package com.example.chapter_two

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
const val CHEAT_USED = "CHEAT_USED"

class CheatViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    var cheatUsed: Boolean
        get() = savedStateHandle.get(CHEAT_USED) ?: false
        set(value) = savedStateHandle.set(CHEAT_USED, value)
}