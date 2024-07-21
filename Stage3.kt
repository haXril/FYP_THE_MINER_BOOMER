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

class Stage3 : AppCompatActivity() {
    private lateinit var c1: CardView
    private lateinit var c2: CardView
    private lateinit var c4: CardView
    private lateinit var c5: CardView
    private lateinit var c7: CardView
    private lateinit var c8: CardView
    private lateinit var c10: CardView
    private lateinit var c11: CardView
    private lateinit var c12: CardView
    private lateinit var c13: CardView
    private lateinit var c15: CardView
    private lateinit var c16: CardView
    private lateinit var c17: CardView
    private lateinit var c18: CardView
    private lateinit var c20: CardView

    private lateinit var r1: ImageView
    private lateinit var r2: ImageView
    private lateinit var r4: ImageView
    private lateinit var r5: ImageView
    private lateinit var r7: ImageView
    private lateinit var r8: ImageView
    private lateinit var r10: ImageView
    private lateinit var r11: ImageView
    private lateinit var r12: ImageView
    private lateinit var r13: ImageView
    private lateinit var r15: ImageView
    private lateinit var r16: ImageView
    private lateinit var r17: ImageView
    private lateinit var r18: ImageView
    private lateinit var r20: ImageView

    private lateinit var timeText: TextView

    private lateinit var n: Button
    private lateinit var y: Button

    private lateinit var mText: TextView
    private lateinit var sText: TextView

    private var randomNumber = (1..15).random()
    private var randomNumber1 = (1..15).random()
    private var randomNumber2 = (1..15).random()
    private var randomNumber3 = (1..15).random()
    private var randomNumber4 = (1..15).random()
    private var randomNumber5 = (1..14).random()
    private var randomNumber6 = (1..14).random()
    private var randomNumber7 = (1..14).random()
    private var randomNumber8 = (1..14).random()
    private var randomNumber9 = (1..14).random()
    private lateinit var videoBackground: VideoView
    private lateinit var levelText: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stage3)
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

        val timer = MyCounter(10000, 1000)
        val gameLevel = intent.getStringExtra("gameLevel").toString()

        timer.start()
        levelText = findViewById(R.id.levelText)

        timeText = findViewById(R.id.timerText)
        sText = findViewById(R.id.stageText)

        sText.text = "Stage : 3"
        levelText.text = gameLevel

        c1 = findViewById(R.id.cube1)
        c2 = findViewById(R.id.cube2)
        c4 = findViewById(R.id.cube4)
        c5 = findViewById(R.id.cube5)
        c7 = findViewById(R.id.cube7)
        c8 = findViewById(R.id.cube8)
        c10 = findViewById(R.id.cube10)
        c11 = findViewById(R.id.cube11)
        c12 = findViewById(R.id.cube12)
        c13 = findViewById(R.id.cube13)
        c15 = findViewById(R.id.cube15)
        c16 = findViewById(R.id.cube16)
        c17 = findViewById(R.id.cube17)
        c18 = findViewById(R.id.cube18)
        c20 = findViewById(R.id.cube20)

        r1 = findViewById(R.id.boom1)
        r2 = findViewById(R.id.boom2)
        r4 = findViewById(R.id.boom4)
        r5 = findViewById(R.id.boom5)
        r7 = findViewById(R.id.boom7)
        r8 = findViewById(R.id.boom8)
        r10 = findViewById(R.id.boom10)
        r11 = findViewById(R.id.boom11)
        r12 = findViewById(R.id.boom12)
        r13 = findViewById(R.id.boom13)
        r15 = findViewById(R.id.boom15)
        r16 = findViewById(R.id.boom16)
        r17 = findViewById(R.id.boom17)
        r18 = findViewById(R.id.boom18)
        r20 = findViewById(R.id.boom20)


        if((randomNumber == 1) || (randomNumber1 == 1) || (randomNumber2 == 1) || (randomNumber3 == 1) || (randomNumber4 == 1)){
            c1.isVisible = false
            r1.isVisible = true
        }
        if((randomNumber == 2) || (randomNumber1 == 2) || (randomNumber2 == 2) || (randomNumber3 == 2) || (randomNumber4 == 2)){
            c2.isVisible = false
            r2.isVisible = true
        }
        if((randomNumber == 3) || (randomNumber1 == 3) || (randomNumber2 == 3) || (randomNumber3 == 3) || (randomNumber4 == 3)){
            c4.isVisible = false
            r4.isVisible = true
        }
        if((randomNumber == 4) || (randomNumber1 == 4) || (randomNumber2 == 4) || (randomNumber3 == 4) || (randomNumber4 == 4)){
            c5.isVisible = false
            r5.isVisible = true
        }
        if((randomNumber == 5) || (randomNumber1 == 5) || (randomNumber2 == 5) || (randomNumber3 == 5) || (randomNumber4 == 5)){
            c7.isVisible = false
            r7.isVisible = true
        }
        if((randomNumber == 6) || (randomNumber1 == 6) || (randomNumber2 == 6) || (randomNumber3 == 6) || (randomNumber4 == 6)){
            c8.isVisible = false
            r8.isVisible = true
        }
        if((randomNumber == 7) || (randomNumber1 == 7) || (randomNumber2 == 7) || (randomNumber3 == 7) || (randomNumber4 == 7)){
            c10.isVisible = false
            r10.isVisible = true
        }
        if((randomNumber == 8) || (randomNumber1 == 8) || (randomNumber2 == 8) || (randomNumber3 == 8) || (randomNumber4 == 8)){
            c11.isVisible = false
            r11.isVisible = true
        }
        if((randomNumber == 9) || (randomNumber1 == 9) || (randomNumber2 == 9) || (randomNumber3 == 9) || (randomNumber4 == 9)){
            c12.isVisible = false
            r12.isVisible = true
        }
        if((randomNumber == 10) || (randomNumber1 == 10) || (randomNumber2 == 10) || (randomNumber3 == 10) || (randomNumber4 == 10)){
            c13.isVisible = false
            r13.isVisible = true
        }
        if((randomNumber == 11) || (randomNumber1 == 11) || (randomNumber2 == 11) || (randomNumber3 == 11) || (randomNumber4 == 11)){
            c15.isVisible = false
            r15.isVisible = true
        }
        if((randomNumber == 12) || (randomNumber1 == 12) || (randomNumber2 == 12) || (randomNumber3 == 12) || (randomNumber4 == 12)){
            c16.isVisible = false
            r16.isVisible = true
        }
        if((randomNumber == 13) || (randomNumber1 == 13) || (randomNumber2 == 13) || (randomNumber3 == 13) || (randomNumber4 == 13)){
            c17.isVisible = false
            r17.isVisible = true
        }
        if((randomNumber == 14) || (randomNumber1 == 14) || (randomNumber2 == 14) || (randomNumber3 == 14) || (randomNumber4 == 14)){
            c18.isVisible = false
            r18.isVisible = true
        }
        if((randomNumber == 15) || (randomNumber1 == 15) || (randomNumber2 == 15) || (randomNumber3 == 15) || (randomNumber4 == 15)){
            c20.isVisible = false
            r20.isVisible = true
        }

    }

    inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {

            val playerName = intent.getStringExtra("username")
            val gameLevel = intent.getStringExtra("gameLevel")
            val playerAge = intent.getStringExtra("age")
            val playerId = intent.getStringExtra("id")
            val playerImageId = intent.getIntExtra("imageId", 0)

            val message = "Are you ready to PLAY ?!"

            note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
        }

        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            timeText.textSize = 50f

            timeText.text = (millisUntilFinished / 1000).toString() + ""
            println("Timer  : " + millisUntilFinished / 1000)
        }
    }

    private fun note1(message: String?, playerId: String?, playerName: String?, playerAge: String?, playerImageId:Int?, gameLevel: String?){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.confirmmessage)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        n = dialog.findViewById(R.id.noBtn)
        y = dialog.findViewById(R.id.yesBtn)
        mText = dialog.findViewById(R.id.messageText)

        mText.text = message

        val dbRef = FirebaseDatabase.getInstance().getReference("Users")

        val dbRf = FirebaseDatabase.getInstance().getReference("History$playerName")

        val em = HistoryPlayer(playerName, gameLevel, 2)

        val userId:String = intent.getStringExtra("userId").toString()

        n.setOnClickListener{

            dbRf.child(userId).setValue(em)
                .addOnCompleteListener{
                    Toast.makeText(this, "Level Save Successful!!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@Stage3, MainActivity2::class.java).also {
                        it.putExtra("username", playerName)
                        it.putExtra("age", playerAge)
                        it.putExtra("id", playerId)
                        it.putExtra("imageId", playerImageId)
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
                    startActivity(Intent(this@Stage3, MainActivity2::class.java).also {
                        it.putExtra("username", playerName)
                        it.putExtra("age", playerAge)
                        it.putExtra("id", playerId)
                        it.putExtra("imageId", playerImageId)
                    })
                    finish()

                    dialog.dismiss()
                }.addOnFailureListener{
                    Toast.makeText(this, "Fail to Save the High Score!!", Toast.LENGTH_LONG).show()
                }
        }


        y.setOnClickListener {
            if (gameLevel == "EASY") {
                Intent(this@Stage3, MedGame3::class.java).also {
                    it.putExtra("boom1", randomNumber)
                    it.putExtra("boom2", randomNumber1)
                    it.putExtra("boom3", randomNumber2)
                    it.putExtra("stage", 3)
                    it.putExtra("username", playerName)
                    it.putExtra("age", playerAge)
                    it.putExtra("id", playerId)
                    it.putExtra("imageId", playerImageId)
                    it.putExtra("userId", userId)
                    it.putExtra("gameLevel", gameLevel)
                    startActivity(it)
                    finish()
                }
            }
            if (gameLevel == "MEDIUM") {
                Intent(this@Stage3, MedGame3::class.java).also {
                    it.putExtra("boom1", randomNumber)
                    it.putExtra("boom2", randomNumber1)
                    it.putExtra("boom3", randomNumber2)
                    it.putExtra("boom4", randomNumber3)
                    it.putExtra("boom5", randomNumber4)
                    it.putExtra("stage", 3)
                    it.putExtra("username", playerName)
                    it.putExtra("age", playerAge)
                    it.putExtra("id", playerId)
                    it.putExtra("imageId", playerImageId)
                    it.putExtra("userId", userId)
                    it.putExtra("gameLevel", gameLevel)
                    startActivity(it)
                    finish()
                }
            }
            if (gameLevel == "HARD") {
                Intent(this@Stage3, MedGame3::class.java).also {
                    it.putExtra("boom1", randomNumber)
                    it.putExtra("boom2", randomNumber1)
                    it.putExtra("boom3", randomNumber2)
                    it.putExtra("boom4", randomNumber3)
                    it.putExtra("boom5", randomNumber4)
                    it.putExtra("boom6", randomNumber5)
                    it.putExtra("boom7", randomNumber6)
                    it.putExtra("boom8", randomNumber7)
                    it.putExtra("boom9", randomNumber8)
                    it.putExtra("boom10", randomNumber9)
                    it.putExtra("stage", 3)
                    it.putExtra("username", playerName)
                    it.putExtra("age", playerAge)
                    it.putExtra("id", playerId)
                    it.putExtra("imageId", playerImageId)
                    it.putExtra("userId", userId)
                    it.putExtra("gameLevel", gameLevel)
                    startActivity(it)
                    finish()
                }
            }
        }
        dialog.show()
    }
}