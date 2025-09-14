package com.example.chapter_two

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.rem
import kotlin.text.toDouble

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val CURRENT_COUNTER_CORRECT = "CURRENT_COUNTER_CORRECT"
class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

   /* init {

        Log.d(TAG, "ViewModel instance created")

    }

    override fun onCleared(){

        Log.d(TAG, "ViewModel instance about to be destroyed")

    } */

   val questionBank = listOf(
       Question(R.string.question_australia, true),
       Question(R.string.question_oceans, true),
       Question(R.string.question_mideast, false),
       Question(R.string.question_africa, false),
       Question(R.string.question_americas, true),
       Question(R.string.question_asia, true)
   )
    var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)
    var counterCorrect
        get() = savedStateHandle.get(CURRENT_COUNTER_CORRECT)?: 0
        set(value) = savedStateHandle.set(CURRENT_COUNTER_CORRECT, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){

        currentIndex = (currentIndex + 1) % questionBank.size

    }
    fun moveToPrev(){

        currentIndex = (currentIndex - 1) % questionBank.size

    }

}