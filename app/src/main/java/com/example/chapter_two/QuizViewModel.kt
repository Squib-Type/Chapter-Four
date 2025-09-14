package com.example.chapter_two

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.rem
import kotlin.text.toDouble

private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

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
    var currentIndex = 0
    var counterCorrect = 0

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
    fun quizCheckScore(){
        val questionsCount = questionBank.size
        val totalScore = (counterCorrect.toDouble()/questionsCount.toDouble()) * 100
        val percentScore = String.format("%.1f", totalScore)
    }

}