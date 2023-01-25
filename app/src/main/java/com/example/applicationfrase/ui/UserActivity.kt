package com.example.applicationfrase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.applicationfrase.R
import com.example.applicationfrase.SecurityPreferences
import com.example.applicationfrase.consts.MotivationConstants
import com.example.applicationfrase.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUsername()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUsername(){
        val name:String= SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if(name != ""){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {

        val name: String = binding.editName.text.toString()

        if (name != "") {

            SecurityPreferences(this).storeName(MotivationConstants.KEY.USER_NAME,name)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}