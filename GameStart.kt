package com.example.theminerboomer

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("DEPRECATION")
class GameStart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_start)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val handler = android.os.Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this@GameStart, GameLevel::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
                it.putExtra("hightScore", intent.getIntExtra("hightScore", 0))
            })
            finish()
        }, 3000)


    }
}