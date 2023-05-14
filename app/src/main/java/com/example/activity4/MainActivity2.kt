package com.example.activity4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.activity4.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMain2Binding

    private var totalQuestion: Int = QuestionAndAnswer.questions.size
    private lateinit var questionTextview: TextView
    private var currentQuestionIndex = 0
    private lateinit var option1: TextView
    private lateinit var option2: TextView
    private lateinit var option3: TextView
    private lateinit var option4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

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

        if (QuestionAndAnswer.correctAnswer.contains(clickedTextView.text)) {
            scoreCount ++
            currentQuestionIndex++
            scoreView.text = scoreCount.toString()
            loadNewQuestion()
        }
        else {
            scoreCount --
            currentQuestionIndex++
            scoreView.text = scoreCount.toString()
            loadNewQuestion()
        }
    }
}