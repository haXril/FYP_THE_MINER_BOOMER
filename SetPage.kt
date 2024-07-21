package com.example.theminerboomer

import HistoryAdap
import HistoryPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import android.content.Intent
import android.widget.ImageView

class SetPage : AppCompatActivity() {

    private lateinit var videoBackground: VideoView
    private lateinit var dbRef: DatabaseReference
    private lateinit var listPlayer: RecyclerView
    private lateinit var userArrayList : ArrayList<HistoryPlayer>
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_set_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listPlayer = findViewById(R.id.listBox)

        listPlayer.visibility = View.VISIBLE

        userArrayList = arrayListOf()

        videoBackground = findViewById(R.id.videoBackground)
        val uri = Uri.parse("android.resource://$packageName/${R.raw.videobackground}")
        videoBackground.setVideoURI(uri)
        videoBackground.start()

        videoBackground.setOnPreparedListener { mp ->
            mp.isLooping = true
        }


        val playerName = intent.getStringExtra("username").toString()
        val gameLevel = intent.getStringExtra("gameLevel")
        val playerAge = intent.getStringExtra("age")
        val playerId = intent.getStringExtra("id")
        val playerImageId = intent.getIntExtra("imageId", 0)

        dbRef = FirebaseDatabase.getInstance().getReference("History$playerName")

        listPlayer.layoutManager = LinearLayoutManager(this)

        listPlayer.visibility= View.VISIBLE

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    for( empSnap in snapshot.children)
                    {
                        val empData = empSnap.getValue(HistoryPlayer::class.java)
                        userArrayList.add(empData!!)
                    }
                    Log.d("TrackONE", userArrayList.toString())
                    val mAdapter = HistoryAdap(userArrayList)
                    listPlayer.adapter = mAdapter
                    listPlayer.visibility= View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        backButton = findViewById(R.id.backBtn)

        backButton.setOnClickListener {
            startActivity(Intent(this@SetPage, MainActivity2::class.java).also {
                it.putExtra("username", playerName)
                it.putExtra("age", playerAge)
                it.putExtra("id", playerId)
                it.putExtra("imageId", playerImageId)
                it.putExtra("gameLevel", gameLevel)
            })
            finish()
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