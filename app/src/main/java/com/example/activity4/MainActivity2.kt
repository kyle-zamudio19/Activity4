package com.example.activity4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.activity4.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMain2Binding

    private lateinit var timer: CountDownTimer
    private var totalQuestion: Int = QuestionAndAnswer.questions.size
    private lateinit var questionTextview: TextView
    private var currentQuestionIndex = 0
    private lateinit var timerTextView: TextView
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        timerTextView = binding.title
        questionTextview = binding.quizQuestion
        option1 = binding.optionA
        option2 = binding.optionB
        option3 = binding.optionC
        option4 = binding.optionD

        loadNewQuestion()

        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)

        timer = object : CountDownTimer(11000, 100) {
            override fun onTick(remaining: Long) {
                timerTextView.text = (remaining / 1000).toString()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                val timer = timerTextView.text
                val alertTitle: String

                if (timer == "0") {
                    alertTitle = "GAME OVER"
                    AlertDialog.Builder(this@MainActivity2)
                        .setTitle(alertTitle)
                        .setPositiveButton("OK") { _, _ ->
                            onCreate(savedInstanceState)
                        }
                        .setCancelable(false)
                        .show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        loadNewQuestion()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }

    private fun loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            val intent = Intent(this@MainActivity2, MainActivity3::class.java)
            intent.putExtra("Score", binding.score.text.toString())
            return startActivity(intent)
        }
        questionTextview.text = QuestionAndAnswer.questions[currentQuestionIndex]
        option1.text = QuestionAndAnswer.options[currentQuestionIndex][0]
        option2.text = QuestionAndAnswer.options[currentQuestionIndex][1]
        option3.text = QuestionAndAnswer.options[currentQuestionIndex][2]
        option4.text = QuestionAndAnswer.options[currentQuestionIndex][3]
    }

    override fun onClick(v: View?) {
        val clickedTextView = v as TextView
        val scoreView = binding.score
        var scoreCount = scoreView.text.toString().toInt()
        val alertTitle: String

        if (QuestionAndAnswer.correctAnswer.contains(clickedTextView.text)) {
            scoreCount++
            currentQuestionIndex++
            scoreView.text = scoreCount.toString()
            alertTitle = "Correct!"
            onStop()
        } else {
            scoreCount--
            currentQuestionIndex++
            scoreView.text = scoreCount.toString()
            alertTitle = "Wrong!"
            onStop()
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setPositiveButton("OK") { _, _ ->
                loadNewQuestion()
                onStart()
            }
            .setCancelable(false)
            .show()
    }
}