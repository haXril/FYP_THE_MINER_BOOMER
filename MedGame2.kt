package com.example.theminerboomer

import HistoryPlayer
import User
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.firebase.database.FirebaseDatabase

class MedGame2 : AppCompatActivity() {

    private lateinit var c1: CardView
    private lateinit var c2: CardView
    private lateinit var c4: CardView
    private lateinit var c5: CardView
    private lateinit var c8: CardView
    private lateinit var c9: CardView
    private lateinit var c10: CardView
    private lateinit var c11: CardView
    private lateinit var c12: CardView
    private lateinit var c13: CardView
    private lateinit var c16: CardView
    private lateinit var c17: CardView
    private lateinit var c19: CardView
    private lateinit var c20: CardView

    private lateinit var r1: ImageView
    private lateinit var r2: ImageView
    private lateinit var r4: ImageView
    private lateinit var r5: ImageView
    private lateinit var r8: ImageView
    private lateinit var r9: ImageView
    private lateinit var r10: ImageView
    private lateinit var r11: ImageView
    private lateinit var r12: ImageView
    private lateinit var r13: ImageView
    private lateinit var r16: ImageView
    private lateinit var r17: ImageView
    private lateinit var r19: ImageView
    private lateinit var r20: ImageView

    private lateinit var s1: ImageView
    private lateinit var s2: ImageView
    private lateinit var s3: ImageView

    private lateinit var sm1: ImageView
    private lateinit var sm2: ImageView
    private lateinit var sm3: ImageView

    private lateinit var timeText: TextView
    private lateinit var sText: TextView

    private lateinit var pb: CardView

    private lateinit var videoBackground: VideoView
    private lateinit var levelText: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_med_game2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        levelText = findViewById(R.id.levelText1)

        videoBackground = findViewById(R.id.videoBackground)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.videobackground}")
        videoBackground.setVideoURI(uri)
        videoBackground.start()

        videoBackground.setOnPreparedListener { mp ->
            mp.isLooping = true
        }

        c1 = findViewById(R.id.cube1)
        c2 = findViewById(R.id.cube2)
        c4 = findViewById(R.id.cube4)
        c5 = findViewById(R.id.cube5)
        c8 = findViewById(R.id.cube8)
        c9 = findViewById(R.id.cube9)
        c10 = findViewById(R.id.cube10)
        c11 = findViewById(R.id.cube11)
        c12 = findViewById(R.id.cube12)
        c13 = findViewById(R.id.cube13)
        c16 = findViewById(R.id.cube16)
        c17 = findViewById(R.id.cube17)
        c19 = findViewById(R.id.cube19)
        c20 = findViewById(R.id.cube20)

        r1 = findViewById(R.id.boom1)
        r2 = findViewById(R.id.boom2)
        r4 = findViewById(R.id.boom4)
        r5 = findViewById(R.id.boom5)
        r8 = findViewById(R.id.boom8)
        r9 = findViewById(R.id.boom9)
        r10 = findViewById(R.id.boom10)
        r11 = findViewById(R.id.boom11)
        r12 = findViewById(R.id.boom12)
        r13 = findViewById(R.id.boom13)
        r16 = findViewById(R.id.boom16)
        r17 = findViewById(R.id.boom17)
        r19 = findViewById(R.id.boom19)
        r20 = findViewById(R.id.boom20)

        s1 = findViewById(R.id.sp1)
        s2 = findViewById(R.id.sp2)
        s3 = findViewById(R.id.sp3)

        sm1 = findViewById(R.id.spm1)
        sm2 = findViewById(R.id.spm2)
        sm3 = findViewById(R.id.spm3)

        val playerName = intent.getStringExtra("username")
        val gameLevel = intent.getStringExtra("gameLevel").toString()
        val playerAge = intent.getStringExtra("age")
        val playerId = intent.getStringExtra("id")
        val playerImageId = intent.getIntExtra("imageId", 0)

        val timer = MyCounter(30000, 1000)

        timer.start()

        timeText = findViewById(R.id.timerText)
        sText = findViewById(R.id.stageText)

        pb = findViewById(R.id.progressBar)

        sText.text = "Stage : ${intent.getIntExtra("stage", 0)}"
        levelText.text = gameLevel

        if (gameLevel == "EASY"){
            val got1 = intent.getIntExtra("boom1", 0)
            val got2 = intent.getIntExtra("boom2", 0)
            val got3 = intent.getIntExtra("boom3", 0)


            val clearCube: MutableList<Int> = ArrayList()

            val message = "GAME OVER!!"

            val gotList: MutableList<Int> = ArrayList()
            val noBoomCubeList: MutableList<Int> = ArrayList()

            val dbRf = FirebaseDatabase.getInstance().getReference("History$playerName")

            val em = HistoryPlayer(playerName, gameLevel, 2)

            val userId:String = intent.getStringExtra("userId").toString()

            if (!(got1 == 1 || got2 == 1 || got3 == 1)){
                noBoomCubeList.add(1)
                c1.setOnClickListener {
                    c1.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 2 || got2 == 2 || got3 == 2)){
                noBoomCubeList.add(1)
                c2.setOnClickListener {
                    c2.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 3 || got2 == 3 || got3 == 3)){
                noBoomCubeList.add(1)
                c4.setOnClickListener {
                    c4.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 4 || got2 == 4 || got3 == 4)){
                noBoomCubeList.add(1)
                c5.setOnClickListener {
                    c5.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 5 || got2 == 5 || got3 == 5)){
                noBoomCubeList.add(1)
                c8.setOnClickListener {
                    c8.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 6 || got2 == 6 || got3 == 6)){
                noBoomCubeList.add(1)
                c9.setOnClickListener {
                    c9.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 7 || got2 == 7 || got3 == 7)){
                noBoomCubeList.add(1)
                c10.setOnClickListener {
                    c10.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 8 || got2 == 8 || got3 == 8)){
                noBoomCubeList.add(1)
                c11.setOnClickListener {
                    c11.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 9 || got2 == 9 || got3 == 9)){
                noBoomCubeList.add(1)
                c12.setOnClickListener {
                    c12.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 10 || got2 == 10 || got3 == 10)){
                noBoomCubeList.add(1)
                c13.setOnClickListener {
                    c13.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 11 || got2 == 11 || got3 == 11)){
                noBoomCubeList.add(1)
                c16.setOnClickListener {
                    c16.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 12 || got2 == 12 || got3 == 12)){
                noBoomCubeList.add(1)
                c17.setOnClickListener {
                    c17.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 13 || got2 == 13 || got3 == 13)){
                noBoomCubeList.add(1)
                c19.setOnClickListener {
                    c19.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 14 || got2 == 14 || got3 == 14)){
                noBoomCubeList.add(1)
                c20.setOnClickListener {
                    c20.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }

            if ((got1 == 1) || (got2 == 1) || (got3 == 1)){
                c1.setOnClickListener {
                    c1.isVisible = false
                    r1.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 2) || (got2 == 2) || (got3 == 2)){
                c2.setOnClickListener {
                    c2.isVisible = false
                    r2.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 3) || (got2 == 3) || (got3 == 3)){
                c4.setOnClickListener {
                    c4.isVisible = false
                    r4.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 4) || (got2 == 4) || (got3 == 4)){
                c5.setOnClickListener {
                    c5.isVisible = false
                    r5.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 5) || (got2 == 5) || (got3 == 5)){
                c8.setOnClickListener {
                    c8.isVisible = false
                    r8.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 6) || (got2 == 6) || (got3 == 6)){
                c9.setOnClickListener {
                    c9.isVisible = false
                    r9.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 7) || (got2 == 7) || (got3 == 7)){
                c10.setOnClickListener {
                    c10.isVisible = false
                    r10.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 8) || (got2 == 8) || (got3 == 8)){
                c11.setOnClickListener {
                    c11.isVisible = false
                    r11.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 9) || (got2 == 9) || (got3 == 9)){
                c12.setOnClickListener {
                    c12.isVisible = false
                    r12.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 10) || (got2 == 10) || (got3 == 10)){
                c13.setOnClickListener {
                    c13.isVisible = false
                    r13.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 11) || (got2 == 11) || (got3 == 11)){
                c16.setOnClickListener {
                    c16.isVisible = false
                    r16.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 12) || (got2 == 12) || (got3 == 12)){
                c17.setOnClickListener {
                    c17.isVisible = false
                    r17.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 13) || (got2 == 13) || (got3 == 13)){
                c19.setOnClickListener {
                    c19.isVisible = false
                    r19.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 14) || (got2 == 14) || (got3 == 14)){
                c20.setOnClickListener {
                    c20.isVisible = false
                    r20.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
        }

        if (gameLevel == "MEDIUM"){
            val got1 = intent.getIntExtra("boom1", 0)
            val got2 = intent.getIntExtra("boom2", 0)
            val got3 = intent.getIntExtra("boom3", 0)
            val got4 = intent.getIntExtra("boom4", 0)
            val got5 = intent.getIntExtra("boom5", 0)


            val clearCube: MutableList<Int> = ArrayList()

            val message = "GAME OVER!!"

            val gotList: MutableList<Int> = ArrayList()
            val noBoomCubeList: MutableList<Int> = ArrayList()

            val dbRf = FirebaseDatabase.getInstance().getReference("History$playerName")

            val em = HistoryPlayer(playerName, gameLevel, 2)

            val userId:String = intent.getStringExtra("userId").toString()

            if (!(got1 == 1 || got2 == 1 || got3 == 1 || got4 == 1 || got5 == 1)){
                noBoomCubeList.add(1)
                c1.setOnClickListener {
                    c1.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 2 || got2 == 2 || got3 == 2 || got4 == 2 || got5 == 2)){
                noBoomCubeList.add(1)
                c2.setOnClickListener {
                    c2.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 3 || got2 == 3 || got3 == 3 || got4 == 3 || got5 == 3)){
                noBoomCubeList.add(1)
                c4.setOnClickListener {
                    c4.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 4 || got2 == 4 || got3 == 4 || got4 == 4 || got5 == 4)){
                noBoomCubeList.add(1)
                c5.setOnClickListener {
                    c5.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 5 || got2 == 5 || got3 == 5 || got4 == 5 || got5 == 5)){
                noBoomCubeList.add(1)
                c8.setOnClickListener {
                    c8.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 6 || got2 == 6 || got3 == 6 || got4 == 6 || got5 == 6)){
                noBoomCubeList.add(1)
                c9.setOnClickListener {
                    c9.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 7 || got2 == 7 || got3 == 7 || got4 == 7 || got5 == 7)){
                noBoomCubeList.add(1)
                c10.setOnClickListener {
                    c10.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 8 || got2 == 8 || got3 == 8 || got4 == 8 || got5 == 8)){
                noBoomCubeList.add(1)
                c11.setOnClickListener {
                    c11.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 9 || got2 == 9 || got3 == 9 || got4 == 9 || got5 == 9)){
                noBoomCubeList.add(1)
                c12.setOnClickListener {
                    c12.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 10 || got2 == 10 || got3 == 10 || got4 == 10 || got5 == 10)){
                noBoomCubeList.add(1)
                c13.setOnClickListener {
                    c13.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 11 || got2 == 11 || got3 == 11 || got4 == 11 || got5 == 11)){
                noBoomCubeList.add(1)
                c16.setOnClickListener {
                    c16.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 12 || got2 == 12 || got3 == 12 || got4 == 12 || got5 == 12)){
                noBoomCubeList.add(1)
                c17.setOnClickListener {
                    c17.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 13 || got2 == 13 || got3 == 13 || got4 == 13 || got5 == 13)){
                noBoomCubeList.add(1)
                c19.setOnClickListener {
                    c19.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 14 || got2 == 14 || got3 == 14 || got4 == 14 || got5 == 14)){
                noBoomCubeList.add(1)
                c20.setOnClickListener {
                    c20.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }

            if ((got1 == 1) || (got2 == 1) || (got3 == 1) || (got4 == 1) || (got5 == 1)){
                c1.setOnClickListener {
                    c1.isVisible = false
                    r1.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 2) || (got2 == 2) || (got3 == 2) || (got4 == 2) || (got5 == 2)){
                c2.setOnClickListener {
                    c2.isVisible = false
                    r2.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 3) || (got2 == 3) || (got3 == 3) || (got4 == 3) || (got5 == 3)){
                c4.setOnClickListener {
                    c4.isVisible = false
                    r4.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 4) || (got2 == 4) || (got3 == 4) || (got4 == 4) || (got5 == 4)){
                c5.setOnClickListener {
                    c5.isVisible = false
                    r5.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 5) || (got2 == 5) || (got3 == 5) || (got4 == 5) || (got5 == 5)){
                c8.setOnClickListener {
                    c8.isVisible = false
                    r8.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 6) || (got2 == 6) || (got3 == 6) || (got4 == 6) || (got5 == 6)){
                c9.setOnClickListener {
                    c9.isVisible = false
                    r9.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 7) || (got2 == 7) || (got3 == 7) || (got4 == 7) || (got5 == 7)){
                c10.setOnClickListener {
                    c10.isVisible = false
                    r10.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 8) || (got2 == 8) || (got3 == 8) || (got4 == 8) || (got5 == 8)){
                c11.setOnClickListener {
                    c11.isVisible = false
                    r11.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 9) || (got2 == 9) || (got3 == 9) || (got4 == 9) || (got5 == 9)){
                c12.setOnClickListener {
                    c12.isVisible = false
                    r12.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 10) || (got2 == 10) || (got3 == 10) || (got4 == 10) || (got5 == 10)){
                c13.setOnClickListener {
                    c13.isVisible = false
                    r13.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 11) || (got2 == 11) || (got3 == 11) || (got4 == 11) || (got5 == 11)){
                c16.setOnClickListener {
                    c16.isVisible = false
                    r16.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 12) || (got2 == 12) || (got3 == 12) || (got4 == 12) || (got5 == 12)){
                c17.setOnClickListener {
                    c17.isVisible = false
                    r17.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 13) || (got2 == 13) || (got3 == 13) || (got4 == 13) || (got5 == 13)){
                c19.setOnClickListener {
                    c19.isVisible = false
                    r19.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 14) || (got2 == 14) || (got3 == 14) || (got4 == 14) || (got5 == 14)){
                c20.setOnClickListener {
                    c20.isVisible = false
                    r20.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
        }


        if (gameLevel == "HARD"){
            val got1 = intent.getIntExtra("boom1", 0)
            val got2 = intent.getIntExtra("boom2", 0)
            val got3 = intent.getIntExtra("boom3", 0)
            val got4 = intent.getIntExtra("boom4", 0)
            val got5 = intent.getIntExtra("boom5", 0)
            val got6 = intent.getIntExtra("boom6", 0)
            val got7 = intent.getIntExtra("boom7", 0)
            val got8 = intent.getIntExtra("boom8", 0)
            val got9 = intent.getIntExtra("boom9", 0)
            val got10 = intent.getIntExtra("boom10", 0)

            val clearCube: MutableList<Int> = ArrayList()

            val message = "GAME OVER!!"

            val gotList: MutableList<Int> = ArrayList()
            val noBoomCubeList: MutableList<Int> = ArrayList()

            val dbRf = FirebaseDatabase.getInstance().getReference("History$playerName")

            val em = HistoryPlayer(playerName, gameLevel, 2)

            val userId:String = intent.getStringExtra("userId").toString()

            if (!(got1 == 1 || got2 == 1 || got3 == 1 || got4 == 1 || got5 == 1 || got6 == 1 || got7 == 1 || got8 == 1 || got9 == 1 || got10 == 1)){
                noBoomCubeList.add(1)
                c1.setOnClickListener {
                    c1.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 2 || got2 == 2 || got3 == 2 || got4 == 2 || got5 == 2 || got6 == 2 || got7 == 2 || got8 == 2 || got9 == 2 || got10 == 2)){
                noBoomCubeList.add(1)
                c2.setOnClickListener {
                    c2.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 3 || got2 == 3 || got3 == 3 || got4 == 3 || got5 == 3 || got6 == 3 || got7 == 3 || got8 == 3 || got9 == 3 || got10 == 3)){
                noBoomCubeList.add(1)
                c4.setOnClickListener {
                    c4.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 4 || got2 == 4 || got3 == 4 || got4 == 4 || got5 == 4 || got6 == 4 || got7 == 4 || got8 == 4 || got9 == 4 || got10 == 4)){
                noBoomCubeList.add(1)
                c5.setOnClickListener {
                    c5.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 5 || got2 == 5 || got3 == 5 || got4 == 5 || got5 == 5 || got6 == 5 || got7 == 5 || got8 == 5 || got9 == 5 || got10 == 5)){
                noBoomCubeList.add(1)
                c8.setOnClickListener {
                    c8.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 6 || got2 == 6 || got3 == 6 || got4 == 6 || got5 == 6 || got6 == 6 || got7 == 6 || got8 == 6 || got9 == 6 || got10 == 6)){
                noBoomCubeList.add(1)
                c9.setOnClickListener {
                    c9.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 7 || got2 == 7 || got3 == 7 || got4 == 7 || got5 == 7 || got6 == 7 || got7 == 7 || got8 == 7 || got9 == 7 || got10 == 7)){
                noBoomCubeList.add(1)
                c10.setOnClickListener {
                    c10.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 8 || got2 == 8 || got3 == 8 || got4 == 8 || got5 == 8 || got6 == 8 || got7 == 8 || got8 == 8 || got9 == 8 || got10 == 8)){
                noBoomCubeList.add(1)
                c11.setOnClickListener {
                    c11.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 9 || got2 == 9 || got3 == 9 || got4 == 9 || got5 == 9 || got6 == 9 || got7 == 9 || got8 == 9 || got9 == 9 || got10 == 9)){
                noBoomCubeList.add(1)
                c12.setOnClickListener {
                    c12.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 10 || got2 == 10 || got3 == 10 || got4 == 10 || got5 == 10 || got6 == 10 || got7 == 10 || got8 == 10 || got9 == 10 || got10 == 10)){
                noBoomCubeList.add(1)
                c13.setOnClickListener {
                    c13.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 11 || got2 == 11 || got3 == 11 || got4 == 11 || got5 == 11 || got6 == 11 || got7 == 11 || got8 == 11 || got9 == 11 || got10 == 11)){
                noBoomCubeList.add(1)
                c16.setOnClickListener {
                    c16.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 12 || got2 == 12 || got3 == 12 || got4 == 12 || got5 == 12 || got6 == 12 || got7 == 12 || got8 == 12 || got9 == 12 || got10 == 12)){
                noBoomCubeList.add(1)
                c17.setOnClickListener {
                    c17.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 13 || got2 == 13 || got3 == 13 || got4 == 13 || got5 == 13 || got6 == 13 || got7 == 13 || got8 == 13 || got9 == 13 || got10 == 13)){
                noBoomCubeList.add(1)
                c19.setOnClickListener {
                    c19.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }
            if (!(got1 == 14 || got2 == 14 || got3 == 14 || got4 == 14 || got5 == 14 || got6 == 14 || got7 == 14 || got8 == 14 || got9 == 14 || got10 == 14)){
                noBoomCubeList.add(1)
                c20.setOnClickListener {
                    c20.background.setTint(Color.WHITE)
                    gotList.add(1)
                    if(gotList.size == noBoomCubeList.size){
                        timer.cancel()

                        dbRf.child(userId).setValue(em)
                            .addOnCompleteListener{
                                Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@MedGame2, Stage3::class.java).also {
                                    it.putExtra("username", playerName)
                                    it.putExtra("age", playerAge)
                                    it.putExtra("id", playerId)
                                    it.putExtra("imageId", playerImageId)
                                    it.putExtra("userId", userId)
                                    it.putExtra("gameLevel", gameLevel)
                                })
                                finish()

                            }.addOnFailureListener{
                                Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                            }
                    }
                }
            }

            if ((got1 == 1) || (got2 == 1) || (got3 == 1) || (got4 == 1) || (got5 == 1) || (got6 == 1) || (got7 == 1) || (got8 == 1) || (got9 == 1) || (got10 == 1)){
                c1.setOnClickListener {
                    c1.isVisible = false
                    r1.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 2) || (got2 == 2) || (got3 == 2) || (got4 == 2) || (got5 == 2) || (got6 == 2) || (got7 == 2) || (got8 == 2) || (got9 == 2) || (got10 == 2)){
                c2.setOnClickListener {
                    c2.isVisible = false
                    r2.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 3) || (got2 == 3) || (got3 == 3) || (got4 == 3) || (got5 == 3) || (got6 == 3) || (got7 == 3) || (got8 == 3) || (got9 == 3) || (got10 == 3)){
                c4.setOnClickListener {
                    c4.isVisible = false
                    r4.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 4) || (got2 == 4) || (got3 == 4) || (got4 == 4) || (got5 == 4) || (got6 == 4) || (got7 == 4) || (got8 == 4) || (got9 == 4) || (got10 == 4)){
                c5.setOnClickListener {
                    c5.isVisible = false
                    r5.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 5) || (got2 == 5) || (got3 == 5) || (got4 == 5) || (got5 == 5) || (got6 == 5) || (got7 == 5) || (got8 == 5) || (got9 == 5) || (got10 == 5)){
                c8.setOnClickListener {
                    c8.isVisible = false
                    r8.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 6) || (got2 == 6) || (got3 == 6) || (got4 == 6) || (got5 == 6) || (got6 == 6) || (got7 == 6) || (got8 == 6) || (got9 == 6) || (got10 == 6)){
                c9.setOnClickListener {
                    c9.isVisible = false
                    r9.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 7) || (got2 == 7) || (got3 == 7) || (got4 == 7) || (got5 == 7) || (got6 == 7) || (got7 == 7) || (got8 == 7) || (got9 == 7) || (got10 == 7)){
                c10.setOnClickListener {
                    c10.isVisible = false
                    r10.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 8) || (got2 == 8) || (got3 == 8) || (got4 == 8) || (got5 == 8) || (got6 == 8) || (got7 == 8) || (got8 == 8) || (got9 == 8) || (got10 == 8)){
                c11.setOnClickListener {
                    c11.isVisible = false
                    r11.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 9) || (got2 == 9) || (got3 == 9) || (got4 == 9) || (got5 == 9) || (got6 == 9) || (got7 == 9) || (got8 == 9) || (got9 == 9) || (got10 == 9)){
                c12.setOnClickListener {
                    c12.isVisible = false
                    r12.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 10) || (got2 == 10) || (got3 == 10) || (got4 == 10) || (got5 == 10) || (got6 == 10) || (got7 == 10) || (got8 == 10) || (got9 == 10) || (got10 == 10)){
                c13.setOnClickListener {
                    c13.isVisible = false
                    r13.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 11) || (got2 == 11) || (got3 == 11) || (got4 == 11) || (got5 == 11) || (got6 == 11) || (got7 == 11) || (got8 == 11) || (got9 == 11) || (got10 == 11)){
                c16.setOnClickListener {
                    c16.isVisible = false
                    r16.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 12) || (got2 == 12) || (got3 == 12) || (got4 == 12) || (got5 == 12) || (got6 == 12) || (got7 == 12) || (got8 == 12) || (got9 == 12) || (got10 == 12)){
                c17.setOnClickListener {
                    c17.isVisible = false
                    r17.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 13) || (got2 == 13) || (got3 == 13) || (got4 == 13) || (got5 == 13) || (got6 == 13) || (got7 == 13) || (got8 == 13) || (got9 == 13) || (got10 == 13)){
                c19.setOnClickListener {
                    c19.isVisible = false
                    r19.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
            if ((got1 == 14) || (got2 == 14) || (got3 == 14) || (got4 == 14) || (got5 == 14) || (got6 == 14) || (got7 == 14) || (got8 == 14) || (got9 == 14) || (got10 == 14)){
                c20.setOnClickListener {
                    c20.isVisible = false
                    r20.isVisible = true
                    clearCube.add(1)
                    if (clearCube.size == 1){
                        s1.isVisible = false
                        sm1.isVisible = true
                    }
                    if (clearCube.size == 2){
                        s2.isVisible = false
                        sm2.isVisible = true
                    }
                    if (clearCube.size == 3){
                        s3.isVisible = false
                        sm3.isVisible = true
                        timer.cancel()
                        note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
                    }
                }
            }
        }
    }

    inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {

            val playerName = intent.getStringExtra("username")
            val gameLevel = intent.getStringExtra("gameLevel")
            val playerAge = intent.getStringExtra("age")
            val playerId = intent.getStringExtra("id")
            val playerImageId = intent.getIntExtra("imageId", 0)

            val ms = "GAME OVER!!"

            note1(ms, playerId, playerName, playerAge, playerImageId, gameLevel)
        }

        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {

            timeText.text = (millisUntilFinished / 1000).toString() + ""
            val layoutParams = pb.layoutParams as ViewGroup.LayoutParams
            layoutParams.width += 20
            pb.layoutParams = layoutParams
            println("Timer  : " + millisUntilFinished / 1000)
        }
    }

    private fun note1(ms: String?, playerId: String?, playerName: String?, playerAge: String?, playerImageId:Int?, gameLevel: String?){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.gameoverplash)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val n = dialog.findViewById<Button>(R.id.bBtn)
        val mText = dialog.findViewById<TextView>(R.id.mText)

        mText.text = ms

        n.setOnClickListener{

            val dbRef = FirebaseDatabase.getInstance().getReference("Users")

            val dbRf = FirebaseDatabase.getInstance().getReference("History$playerName")

            val em = HistoryPlayer(playerName, gameLevel, 2)

            val userId:String = intent.getStringExtra("userId").toString()

            dbRf.child(userId).setValue(em)
                .addOnCompleteListener{
                    Toast.makeText(this, "Level Save Successful!!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MedGame2, MainActivity2::class.java).also {
                        it.putExtra("username", playerName)
                        it.putExtra("age", playerAge)
                        it.putExtra("id", playerId)
                        it.putExtra("imageId", playerImageId)
                        it.putExtra("userId", userId)
                    })
                    finish()

                    dialog.dismiss()
                }.addOnFailureListener{
                    Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                }

            val dataUpdate = User(playerId, playerName, playerAge, playerImageId, 2)

            dbRef.child(playerId.toString()).setValue(dataUpdate)
                .addOnCompleteListener{
                    Toast.makeText(this, "High Score Save Successful!!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MedGame2, MainActivity2::class.java).also {
                        it.putExtra("username", playerName)
                        it.putExtra("age", playerAge)
                        it.putExtra("id", playerId)
                        it.putExtra("imageId", playerImageId)
                        it.putExtra("userId", userId)
                    })
                    finish()

                    dialog.dismiss()
                }.addOnFailureListener{
                    Toast.makeText(this, "Fail to Save the High Score!!", Toast.LENGTH_LONG).show()
                }
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