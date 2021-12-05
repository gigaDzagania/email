package com.example.emailregistracia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    private lateinit var textViewUid: TextView
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textViewUid = findViewById(R.id.textViewUid)
        buttonBack = findViewById(R.id.buttonBack)

        textViewUid.text = FirebaseAuth.getInstance().currentUser?.uid

        buttonBack.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

    }
}
