package com.example.emailregistracia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var emailText: EditText
    private lateinit var password1: EditText
    private lateinit var password2: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailText = findViewById(R.id.emailText)
        password1 = findViewById(R.id.password1)
        password2 = findViewById(R.id.password2)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {

            val email = emailText.text.toString()
            val password = password1.text.toString()

            when {
                emailText.text.isEmpty() || !emailText.text.contains("@") ->
                    Toast.makeText(this, "შიყვანეთ სწორი E-mail -ი !", Toast.LENGTH_SHORT).show()
                password1.text.length < 9 || !password1.text.contains(Regex("[0-9]"))->
                    Toast.makeText(this, "შიყვანეთ პაროლი(შეიცავდეს ციფრებს) !", Toast.LENGTH_SHORT).show()
                !password2.text.contains(password1.text) || password2.text.length != password1.text.length ->
                    Toast.makeText(this, "გაიმეორეთ პაროლი სწორად !", Toast.LENGTH_SHORT).show()
                else ->{
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                login()
                                Toast.makeText(this, "რეგისტრაცია წარმატებით დასრულდა", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "შიყვანეთ სწორი E-mail -ი !", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

        }
    }

    private fun login(){
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }
}
