package com.example.theminerboomer

import User
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView
import UserAdapter
import android.net.Uri
import android.view.View
import android.widget.VideoView
import com.google.firebase.database.DatabaseReference

class MainActivity2 : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    private lateinit var userProfileBtn: CircleImageView
    private lateinit var userSetBtn: ImageView

    private lateinit var plyBtn: Button

    private lateinit var username: TextView

    private lateinit var userArrayList : ArrayList<User>

    private lateinit var listPlayer: RecyclerView

    private lateinit var videoBackground: VideoView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
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

        userArrayList = arrayListOf()

        listPlayer = findViewById(R.id.listBox)

        username = findViewById(R.id.usernameTag)

        userProfileBtn = findViewById(R.id.userProfileBtn)

        username.text = intent.getStringExtra("username").toString()

        val imageId:Int = intent.getIntExtra("imageId", 0)

        if(imageId == 1){
            userProfileBtn.setImageResource(R.drawable.avatar1)
        }
        if(imageId == 2){
            userProfileBtn.setImageResource(R.drawable.avatar2)
        }
        if(imageId == 3){
            userProfileBtn.setImageResource(R.drawable.avatar3)
        }
        if(imageId == 4){
            userProfileBtn.setImageResource(R.drawable.avatar4)
        }
        if(imageId == 5){
            userProfileBtn.setImageResource(R.drawable.avatar5)
        }
        if(imageId == 6){
            userProfileBtn.setImageResource(R.drawable.avatar6)
        }
        if(imageId == 7){
            userProfileBtn.setImageResource(R.drawable.avatar7)
        }
        if(imageId == 8){
            userProfileBtn.setImageResource(R.drawable.avatar8)
        }
        if(imageId == 9){
            userProfileBtn.setImageResource(R.drawable.avatar9)
        }

        userSetBtn = findViewById(R.id.setBtn)

        plyBtn = findViewById(R.id.playBtn)

        plyBtn.isEnabled

        listPlayer.visibility = View.VISIBLE

        userProfileBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity2, UserPage::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
            })
            finish()
        }

        userSetBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity2, SetPage::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
            })
            finish()
        }

        plyBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity2, GameStart::class.java).also {
                it.putExtra("username", intent.getStringExtra("username"))
                it.putExtra("age", intent.getStringExtra("age"))
                it.putExtra("id", intent.getStringExtra("id"))
                it.putExtra("imageId", intent.getIntExtra("imageId", 0))
                it.putExtra("hightScore", intent.getIntExtra("hightScore", 0))
            })
            finish()
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        listPlayer.layoutManager = LinearLayoutManager(this)

        listPlayer.visibility= View.VISIBLE

        dbRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    for( empSnap in snapshot.children)
                    {
                        val empData = empSnap.getValue(User::class.java)
                        userArrayList.add(empData!!)
                    }
                    Log.d("TrackONE", userArrayList.toString())
                    val mAdapter = UserAdapter(userArrayList)
                    listPlayer.adapter = mAdapter
                    listPlayer.visibility= View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
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