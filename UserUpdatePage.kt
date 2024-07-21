package com.example.theminerboomer

import User
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView

class UserUpdatePage : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var age: EditText

    private lateinit var buttonSubmit: Button
    private lateinit var buttonBackPage: ImageView
    private lateinit var editImageBtn: ImageView

    private lateinit var avatarUser: CircleImageView

    private var imgId: MutableList<Int> = ArrayList()

    private lateinit var videoBackground: VideoView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_update_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("id").toString()

        videoBackground = findViewById(R.id.videoBackground)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.videobackground}")
        videoBackground.setVideoURI(uri)
        videoBackground.start()

        videoBackground.setOnPreparedListener { mp ->
            mp.isLooping = true
        }

        username = findViewById(R.id.username2)
        age = findViewById(R.id.age2)

        buttonSubmit = findViewById(R.id.submitButton1)
        buttonBackPage = findViewById(R.id.backButtonPage1)
        editImageBtn = findViewById(R.id.editImage)

        avatarUser = findViewById(R.id.avatar)

        username.setText(intent.getStringExtra("username").toString())
        age.setText(intent.getStringExtra("age").toString())

        imgId = arrayListOf()

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

        buttonSubmit.setOnClickListener {
            update(username.text.toString(),
                age.text.toString(),
                idImage
            )
        }

        buttonBackPage.setOnClickListener {
            startActivity(Intent(this@UserUpdatePage, UserPage::class.java).also {
                it.putExtra("id", id)
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("imageId", idImage)
            })
            finish()
        }

        editImageBtn.setOnClickListener{
            function1()
        }
    }

    private fun update(u: String, a: String, i: Int){
        val id = intent.getStringExtra("id").toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("Users").child(id)

        if(imgId.isEmpty()){
            val userInfo = User(id, u, a, i)
            dbRef.setValue(userInfo)

            val txtUsername = username.text.toString()
            val txtAge = age.text.toString()


            startActivity(Intent(this@UserUpdatePage, UserPage::class.java).also {
                it.putExtra("id", id)
                it.putExtra("username", txtUsername)
                it.putExtra("age", txtAge)
                it.putExtra("imageId", i)
            })
            finish()

        }else{
            val userInfo = User(id, u, a, imgId[0])
            dbRef.setValue(userInfo)

            val txtUsername = username.text.toString()
            val txtAge = age.text.toString()


            startActivity(Intent(this@UserUpdatePage, UserPage::class.java).also {
                it.putExtra("id", id)
                it.putExtra("username", txtUsername)
                it.putExtra("age", txtAge)
                it.putExtra("imageId", imgId[0])
            })
            finish()
        }
    }

    private fun function1(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.avatarset)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val n = dialog.findViewById<ImageView>(R.id.buttonClose)
        val img1 = dialog.findViewById<ImageView>(R.id.avatar1)
        val img2 = dialog.findViewById<ImageView>(R.id.avatar2)
        val img3 = dialog.findViewById<ImageView>(R.id.avatar3)
        val img4 = dialog.findViewById<ImageView>(R.id.avatar4)
        val img5 = dialog.findViewById<ImageView>(R.id.avatar5)
        val img6 = dialog.findViewById<ImageView>(R.id.avatar6)
        val img7 = dialog.findViewById<ImageView>(R.id.avatar7)
        val img8 = dialog.findViewById<ImageView>(R.id.avatar8)
        val img9 = dialog.findViewById<ImageView>(R.id.avatar9)

        img1.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar1)
            imgId.add(1)
            dialog.dismiss()
        }
        img2.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar2)
            imgId.add(2)
            dialog.dismiss()
        }
        img3.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar3)
            imgId.add(3)
            dialog.dismiss()
        }
        img4.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar4)
            imgId.add(4)
            dialog.dismiss()
        }
        img5.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar5)
            imgId.add(5)
            dialog.dismiss()
        }
        img6.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar6)
            imgId.add(6)
            dialog.dismiss()
        }
        img7.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar7)
            imgId.add(7)
            dialog.dismiss()
        }
        img8.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar8)
            imgId.add(8)
            dialog.dismiss()
        }
        img9.setOnClickListener {
            avatarUser.setImageResource(R.drawable.avatar9)
            imgId.add(9)
            dialog.dismiss()
        }

        n.setOnClickListener{
            dialog.dismiss()
        }

        dialog.show()
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