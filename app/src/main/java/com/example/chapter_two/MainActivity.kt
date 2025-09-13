package com.example.chapter_two

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.example.chapter_two.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
        )

    private var currentIndex = 0
    private var counterCorrect = 0

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate(Bundle) called")

        // trueButton = findViewById(R.id.true_button)
        // falseButton = findViewById(R.id.false_button)


        binding.trueButton.setOnClickListener {
           /* val snackBar = Snackbar.make(
                it,
                "Correct",
                LENGTH_SHORT
            )
            snackBar.show() */
            checkAnswer(true)

        }

        binding.falseButton.setOnClickListener {
           /* val snackBar = Snackbar.make(
                it,
                "Incorrect",
                LENGTH_SHORT
            )
            snackBar.show() */
            checkAnswer(false)

        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            // val questionTextResId = questionBank[currentIndex].textResId
            // binding.questionTextView.setText(questionTextResId)
            updateQuestion()
            checkScore()

        }
        binding.prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            // val questionTextResId = questionBank[currentIndex].textResId
            // binding.questionTextView.setText(questionTextResId)
            updateQuestion()

        }
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        // val questionTextResId = questionBank[currentIndex].textResId
        // binding.questionTextView.setText(questionTextResId)
        updateQuestion()


        /*
        trueButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            )
                .show()
        }

        falseButton.setOnClickListener {
            Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            )
                .show()
        } */
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResID = if (userAnswer == correctAnswer) {

            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }
        if (currentIndex == questionBank.size) {
            checkScore()
        }


        binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
        binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

        Toast.makeText( this,
            messageResID,
            Toast.LENGTH_SHORT)
            .show()

    }
    private fun checkScore(){
        val questionsCount = questionBank.size
        val totalScore = counterCorrect.toDouble()/questionsCount.toDouble()

        val percentScore = String.format("%.1f", totalScore)

        Toast.makeText(
            this,
            percentScore,
            Toast.LENGTH_LONG
        ).show()

    }
}