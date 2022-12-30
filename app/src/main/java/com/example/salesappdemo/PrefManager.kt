package com.example.salesappdemo

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PrefManager(context: Context) {
    private val PRIVATE_MODE = 0
    private val EMAIL = "email"

    private val pref : SharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    val editor : SharedPreferences.Editor = pref.edit()

    fun setLogin(isLogin:Boolean){
        editor.putBoolean(IS_LOGIN,isLogin)
        editor.commit()
    }

    fun setEmail(username:String){
        editor.putString(EMAIL,username)
        Log.d("145",username)
    }
    fun setPassword(password:String){
        editor.putString(PASSWORD,password)
    }

    fun isLogin() : Boolean {
        return pref.getBoolean(IS_LOGIN,false)
    }
    fun getEmail(): String? {
        return pref.getString(EMAIL,"")

    }
    companion object {
        private val PREF_NAME = "sharedPreference"
        private val IS_LOGIN = "is_login"
        const val PASSWORD = "pass"
    }

}
