package com.example.salesappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashScreen1 : AppCompatActivity() {
    private lateinit var prefManager: PrefManager

//    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        prefManager = PrefManager(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)




        val handler = Handler(Looper.getMainLooper())
       handler.postDelayed({

            if(prefManager.isLogin()){
                startActivity(Intent(this,MainActivity::class.java))

            }else{
                startActivity(Intent(this,LoginActivity::class.java))

            }


                finish()
            },  1500)
        }





}