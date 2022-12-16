package com.example.salesappdemo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var prefManager: PrefManager
    lateinit var emailString: String
    lateinit var passwordString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefManager = PrefManager(this)
        email = findViewById(R.id.email_filed)
        password=findViewById(R.id.passWordText)
        val loginBtn: Button = findViewById(R.id.login_button)

       // checkLogin()



        loginBtn.setOnClickListener {
            emailString = email.text.toString().trim()
            passwordString = password.text.toString().trim()
            prefManager.setEmail(emailString)
            prefManager.setPassword(passwordString)
            prefManager.setLogin(true)
            startActivity(Intent(this, MainActivity::class.java))

        }
    }





}