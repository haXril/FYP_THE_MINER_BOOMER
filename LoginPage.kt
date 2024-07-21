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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPage : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var registerLink: TextView
    private lateinit var loginBtn: Button
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private lateinit var videoBackground: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)
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

        username = findViewById(R.id.email)
        registerLink = findViewById(R.id.registerText)
        loginBtn = findViewById(R.id.loginbtn)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Users")

        registerLink.setOnClickListener {
            startActivity(Intent(this@LoginPage, RegisterPage::class.java))
            finish()
        }

        loginBtn.setOnClickListener{
            val username = username.text.toString()

            if(username.isNotEmpty())
            {
                logIn(username)
            }else{
                Toast.makeText(this@LoginPage,
                    "All fields are mandatory", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun logIn(username: String) {
        databaseReference.orderByChild("userName").equalTo(username).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()){
                    for(userSnapshot in dataSnapshot.children){
                        val users = userSnapshot.getValue(User::class.java)

                        if(users != null && users.userName == username){
                                Toast.makeText(this@LoginPage, "Login Successful!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@LoginPage, MainActivity2::class.java).also {
                                    it.putExtra("username", users.userName)
                                    it.putExtra("age", users.userAge)
                                    it.putExtra("id", users.userId)
                                    it.putExtra("imageId", users.imageId)
                                    it.putExtra("hightScore", users.hScore)
                                })
                                finish()
                                return
                        }
                    }
                }
                if(!dataSnapshot.exists()){
                    Toast.makeText(this@LoginPage, "You don't register YET!!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@LoginPage, RegisterPage::class.java))
                    finish()
                    return
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@LoginPage, "Database Error!!", Toast.LENGTH_LONG).show()
            }
        })
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