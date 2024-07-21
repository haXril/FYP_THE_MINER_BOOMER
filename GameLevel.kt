package com.example.theminerboomer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameLevel : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_level)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

        btn1.setOnClickListener {
            startActivity(Intent(this@GameLevel, Stage1::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
                it.putExtra("gameLevel", "EASY")
                it.putExtra("hightScore", intent.getIntExtra("hightScore", 0))
            })
            finish()
        }

        btn2.setOnClickListener {
            startActivity(Intent(this@GameLevel, Stage1::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
                it.putExtra("gameLevel", "MEDIUM")
                it.putExtra("hightScore", intent.getIntExtra("hightScore", 0))
            })
            finish()
        }

        btn3.setOnClickListener {
            startActivity(Intent(this@GameLevel, Stage1::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
                it.putExtra("gameLevel", "HARD")
                it.putExtra("hightScore", intent.getIntExtra("hightScore", 0))
            })
            finish()
        }
    }
}