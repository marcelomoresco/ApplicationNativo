package com.example.applicationfrase

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    private val shared: SharedPreferences = context.getSharedPreferences(
        "Motivation",
        Context.MODE_PRIVATE
    )


    fun storeName(key:String,name:String){
        shared.edit().putString(key, name).apply()
    }

    fun getString(key:String): String{
        return shared.getString(key,"") ?: ""
    }
}