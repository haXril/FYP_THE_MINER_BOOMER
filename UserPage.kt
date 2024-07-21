package com.example.theminerboomer

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView

class UserPage : AppCompatActivity() {

    private lateinit var username: TextView
    private lateinit var age: TextView

    private lateinit var avatarUser: CircleImageView
    private lateinit var backButton: ImageView
    private lateinit var editButton: Button
    private lateinit var deleteAccBtn: Button

    private lateinit var videoBackground: VideoView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_page)
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


        username = findViewById(R.id.username1)
        age = findViewById(R.id.age1)

        avatarUser = findViewById(R.id.avatar)

        backButton = findViewById(R.id.btnBackPage)
        editButton = findViewById(R.id.editBtnPro)
        deleteAccBtn = findViewById(R.id.deleteAcc)

        username.text = intent.getStringExtra("username").toString()
        age.text = intent.getStringExtra("age").toString()

        val idImage: Int = intent.getIntExtra("imageId", 0)

        if(idImage == 1){
            avatarUser.setImageResource(R.drawable.avatar1)
        }
        if(idImage == 2){
            avatarUser.setImageResource(R.drawable.avatar2)
        }
        if(idImage == 3){
            avatarUser.setImageResource(R.drawable.avatar3)
        }
        if(idImage == 4){
            avatarUser.setImageResource(R.drawable.avatar4)
        }
        if(idImage == 5){
            avatarUser.setImageResource(R.drawable.avatar5)
        }
        if(idImage == 6){
            avatarUser.setImageResource(R.drawable.avatar6)
        }
        if(idImage == 7){
            avatarUser.setImageResource(R.drawable.avatar7)
        }
        if(idImage == 8){
            avatarUser.setImageResource(R.drawable.avatar8)
        }
        if(idImage == 9){
            avatarUser.setImageResource(R.drawable.avatar9)
        }

        val idUser = intent.getStringExtra("id").toString()

        deleteAccBtn.setOnClickListener {
            deleteAcc(idUser)
        }

        editButton.setOnClickListener {
            startActivity(Intent(this@UserPage, UserUpdatePage::class.java).also {
                it.putExtra("username", username.text)
                it.putExtra("age", age.text)
                it.putExtra("id", idUser)
                it.putExtra("imageId", idImage)
            })
            finish()
        }

        backButton.setOnClickListener {
            startActivity(Intent(this@UserPage, MainActivity2::class.java).also {
                it.putExtra("username", username.text)
                it.putExtra("age", age.text)
                it.putExtra("id", idUser)
                it.putExtra("imageId", idImage)
            })
            finish()
        }
    }

    private fun deleteAcc(idUser: String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Users").child(idUser)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Account success deleted", Toast.LENGTH_LONG).show()
            val intent = Intent( this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
                error -> Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
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