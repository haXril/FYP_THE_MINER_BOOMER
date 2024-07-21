package com.example.theminerboomer

import HistoryPlayer
import User
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.firebase.database.FirebaseDatabase

class MedGame3 : AppCompatActivity() {
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

    private lateinit var s1: ImageView
    private lateinit var s2: ImageView
    private lateinit var s3: ImageView

    private lateinit var sm1: ImageView
    private lateinit var sm2: ImageView
    private lateinit var sm3: ImageView

    private lateinit var timeText: TextView
    private lateinit var sText: TextView

    private lateinit var pb: CardView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_med_game3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        s1 = findViewById(R.id.sp1)
        s2 = findViewById(R.id.sp2)
        s3 = findViewById(R.id.sp3)

        sm1 = findViewById(R.id.spm1)
        sm2 = findViewById(R.id.spm2)
        sm3 = findViewById(R.id.spm3)

        val playerName = intent.getStringExtra("username")
        val gameLevel = intent.getStringExtra("gameLevel")
        val playerAge = intent.getStringExtra("age")
        val playerId = intent.getStringExtra("id")
        val playerImageId = intent.getIntExtra("imageId", 0)

        val dbRf = FirebaseDatabase.getInstance().getReference("History$playerName")

        val em = HistoryPlayer(playerName, gameLevel, 3)

        val userId:String = intent.getStringExtra("userId").toString()

        val timer = MyCounter(30000, 1000)

        timer.start()

        timeText = findViewById(R.id.timerText)
        sText = findViewById(R.id.stageText)

        pb = findViewById(R.id.progressBar)

        sText.text = "Stage : ${intent.getIntExtra("stage", 0)}"

        val got1 = intent.getIntExtra("boom1", 0)
        val got2 = intent.getIntExtra("boom2", 0)
        val got3 = intent.getIntExtra("boom3", 0)
        val got4 = intent.getIntExtra("boom4", 0)
        val got5 = intent.getIntExtra("boom5", 0)

        val clearCube: MutableList<Int> = ArrayList()

        val message = "GAME OVER!!"

        val gotList: MutableList<Int> = ArrayList()
        val noBoomCubeList: MutableList<Int> = ArrayList()

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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c7.setOnClickListener {
                c7.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c8.setOnClickListener {
                c8.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c15.setOnClickListener {
                c15.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c16.setOnClickListener {
                c16.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c17.setOnClickListener {
                c17.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c18.setOnClickListener {
                c18.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
                            })
                            finish()

                        }.addOnFailureListener{
                            Toast.makeText(this, "Fail to Save the Level!!", Toast.LENGTH_LONG).show()
                        }
                }
            }
        }
        if (!(got1 == 15 || got2 == 15 || got3 == 15 || got4 == 15 || got5 == 15)){
            noBoomCubeList.add(1)
            c20.setOnClickListener {
                c20.background.setTint(Color.WHITE)
                gotList.add(1)
                if(gotList.size == noBoomCubeList.size){
                    timer.cancel()

                    dbRf.child(userId).setValue(em)
                        .addOnCompleteListener{
                            Toast.makeText(this, "Nice!!", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MedGame3, Stage4::class.java).also {
                                it.putExtra("username", playerName)
                                it.putExtra("age", playerAge)
                                it.putExtra("id", playerId)
                                it.putExtra("imageId", playerImageId)
                                it.putExtra("userId", userId)
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
            c7.setOnClickListener {
                c7.isVisible = false
                r7.isVisible = true
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
            c15.setOnClickListener {
                c15.isVisible = false
                r15.isVisible = true
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
        if ((got1 == 13) || (got2 == 13) || (got3 == 13) || (got4 == 13) || (got5 == 13)){
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
        if ((got1 == 14) || (got2 == 14) || (got3 == 14) || (got4 == 14) || (got5 == 14)){
            c18.setOnClickListener {
                c18.isVisible = false
                r18.isVisible = true
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
        if ((got1 == 15) || (got2 == 15) || (got3 == 15) || (got4 == 15) || (got5 == 15)){
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

    inner class MyCounter(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {
        override fun onFinish() {

            val playerName = intent.getStringExtra("username")
            val gameLevel = intent.getStringExtra("gameLevel")
            val playerAge = intent.getStringExtra("age")
            val playerId = intent.getStringExtra("id")
            val playerImageId = intent.getIntExtra("imageId", 0)
            val message = "GAME OVER!!"

            note1(message, playerId, playerName, playerAge, playerImageId, gameLevel)
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

            val em = HistoryPlayer(playerName, gameLevel, 3)

            val userId:String = intent.getStringExtra("userId").toString()

            dbRf.child(userId).setValue(em)
                .addOnCompleteListener{
                    Toast.makeText(this, "Level Save Successful!!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@MedGame3, MainActivity2::class.java).also {
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
                    startActivity(Intent(this@MedGame3, MainActivity2::class.java).also {
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
}