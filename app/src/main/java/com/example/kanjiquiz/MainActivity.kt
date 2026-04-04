package com.example.kanjiquiz

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

data class Example(
    val jp: String,
    val en: String,
    val distractors: List<String>
)

class MainActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var kanjiText: TextView
    private lateinit var buttons: List<Button>
    private lateinit var resultText: TextView

    private var currentAnswer = ""
    private var score = 0
    private var total = 0

    private val examples = listOf(
        Example("ご飯を食べる", "eat rice",
            listOf("drink water", "sleep", "walk")),
        Example("朝ご飯を食べる", "eat breakfast",
            listOf("skip breakfast", "cook", "shop"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kanjiText = findViewById(R.id.kanjiText)
        questionText = findViewById(R.id.questionText)
        resultText = findViewById(R.id.resultText)

        buttons = listOf(
            findViewById(R.id.btn1),
            findViewById(R.id.btn2),
            findViewById(R.id.btn3),
            findViewById(R.id.btn4)
        )

        loadQuestion()
    }

    private fun loadQuestion() {
        val example = examples.random()

        kanjiText.text = "Kanji: 食"
        questionText.text = example.jp

        val options = (example.distractors + example.en).shuffled()
        currentAnswer = example.en

        for (i in buttons.indices) {
            buttons[i].text = options[i]
            buttons[i].setOnClickListener {
                checkAnswer(options[i])
            }
        }
    }

    private fun checkAnswer(selected: String) {
        total++

        if (selected == currentAnswer) {
            score++
            resultText.text = "Benar!"
        } else {
            resultText.text = "Salah! Jawaban: $currentAnswer"
        }

        resultText.append("\nScore: $score/$total")

        // Load next question
        buttons[0].postDelayed({
            loadQuestion()
        }, 1500)
    }
}

