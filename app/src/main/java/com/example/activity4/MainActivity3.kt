package com.example.activity4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activity4.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val score = intent.getStringExtra("Score")
        binding.score0.text = getString(R.string._02, score)
    }
}