package com.example.chapter_two

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.example.chapter_two.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private val quizViewModel: QuizViewModel by viewModels()

    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val answerShown =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
            if (answerShown) {
                quizViewModel.currentQuestionCheated()
            }
        }
    }

    /*
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
    */
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate(Bundle) called")
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

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
            // currentIndex = (currentIndex + 1) % questionBank.size

            quizViewModel.moveToNext()

            // val questionTextResId = questionBank[currentIndex].textResId
            // binding.questionTextView.setText(questionTextResId)
            updateQuestion()

        }

        binding.prevButton.setOnClickListener {
            // currentIndex = (currentIndex - 1) % questionBank.size

            quizViewModel.moveToPrev()

            // val questionTextResId = questionBank[currentIndex].textResId
            // binding.questionTextView.setText(questionTextResId)
            updateQuestion()

        }

        binding.cheatButton.setOnClickListener {
            //start Cheat Activity
            // val intent = Intent(this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)

            // startActivity(intent)
            cheatLauncher.launch(intent)


        }

        binding.questionTextView.setOnClickListener {
            // currentIndex = (currentIndex + 1) % questionBank.size

            quizViewModel.moveToNext()

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
        // val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText

        binding.questionTextView.setText(questionTextResId)

        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
    }
    private fun checkAnswer(userAnswer:Boolean){
        // val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.currentQuestionCheated -> R.string.judgment_toast
            userAnswer == correctAnswer ->  {
                quizViewModel.counterCorrect++
                R.string.correct_toast
            }
            else -> R.string.incorrect_toast
        }
       /* val messageResID = if (userAnswer == correctAnswer) {
            quizViewModel.counterCorrect++
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        } */

        Toast.makeText( this,
            messageResId,
            Toast.LENGTH_SHORT)
            .show()

        if ((quizViewModel.currentIndex + 1) == quizViewModel.questionBank.size) {
            checkScore()
            quizViewModel.counterCorrect = 0
        }

        binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
        binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)

    }
    private fun checkScore(){
        val questionsCount = quizViewModel.questionBank.size
        val totalScore = (quizViewModel.counterCorrect.toDouble()/questionsCount.toDouble()) * 100
        val percentScore = String.format("%.1f", totalScore)


        Toast.makeText(
            this,
            "$percentScore %",
            Toast.LENGTH_LONG
        ).show()

    }
}