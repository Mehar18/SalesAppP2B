package com.example.salesappdemo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var prefManager: PrefManager
    lateinit var emailString: String
    var logouttxt:String? = " "
    var logoutSuctxt:String? = " "
    lateinit var passwordString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefManager = PrefManager(this)
        email = findViewById(R.id.email_filed)
        password=findViewById(R.id.passWordText)
        val loginBtn: Button = findViewById(R.id.login_button)
        val logText:TextView = findViewById(R.id.logOutS)
        val logsuc:TextView=findViewById(R.id.logOutSu)
        val linearLayout:LinearLayout=findViewById(R.id.linearlayout)
        val intent=intent.extras
        if (intent==null){
            logouttxt=" "
            logoutSuctxt =" "
            logText.text = logouttxt
            logsuc.text=logoutSuctxt
            logsuc.setBackgroundColor(resources.getColor(R.color.white))
            logText.setBackgroundColor(resources.getColor(R.color.white))
        }else{

            linearLayout.visibility = View.VISIBLE
            logouttxt = intent.getString("log")
            logoutSuctxt=intent.getString("suc")

            logsuc.text = logoutSuctxt
            logsuc.setBackgroundColor(resources.getColor(R.color.background))

            logText.text = logouttxt
            logText.setBackgroundColor(resources.getColor(R.color.background))

        }
//        logouttxt = intent.getStringExtra("log")
//        val logText:TextView = findViewById(R.id.logOutS)
//        logText.text = logouttxt
//        if (logouttxt!!.isEmpty()) {
//            logText.setBackgroundColor(resources.getColor(R.color.white))
//
//        }else{
//            logText.setBackgroundColor(resources.getColor(R.color.background))


       // }


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