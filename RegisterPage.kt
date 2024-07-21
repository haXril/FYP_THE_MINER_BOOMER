package com.example.theminerboomer

import User
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterPage : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    private lateinit var username: EditText
    private lateinit var age: EditText
    private lateinit var registerBtn: Button
    private lateinit var logInLink: TextView

    private lateinit var videoBackground: VideoView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        videoBackground = findViewById(R.id.videoBackground)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.videobackground}")
        videoBackground.setVideoURI(uri)
        videoBackground.start()

        videoBackground.setOnPreparedListener { mp ->
            mp.isLooping = true
        }

        username = findViewById(R.id.username)
        age = findViewById(R.id.age)
        registerBtn = findViewById(R.id.registerBtn)
        logInLink = findViewById(R.id.logInLink)

        Toast.makeText(this, "Submit", Toast.LENGTH_LONG).show()

        registerBtn.setOnClickListener{

            saveData(username.text.toString(), age.text.toString())
        }

        logInLink.setOnClickListener{
            val i = Intent(this, LoginPage::class.java)
            startActivity(i)
        }
    }

    private fun saveData(n: String, a: String) {

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        val userId:String = dbRef.push().key!!

        val em = User(userId, n, a, 1, 0)

        dbRef.child(userId).setValue(em)
            .addOnCompleteListener{
                Toast.makeText(this, "SignUp Successful!!", Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Fail to SignUp!!", Toast.LENGTH_LONG).show()
            }

        val i = Intent(this, LoginPage::class.java)
        startActivity(i)
    }

    override fun onResume() {
        videoBackground.resume()
        super.onResume()
    }

    override fun onRestart() {
        videoBackground.start()
        super.onRestart()
    }

    override fun onPause() {
        videoBackground.suspend()
        super.onPause()
    }

    override fun onDestroy() {
        videoBackground.stopPlayback()
        super.onDestroy()
    }
}