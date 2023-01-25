package com.example.applicationfrase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.applicationfrase.R
import com.example.applicationfrase.SecurityPreferences
import com.example.applicationfrase.consts.MotivationConstants
import com.example.applicationfrase.data.Mock
import com.example.applicationfrase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()
        handleUsername()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id== R.id.button_new_phrase){
            handleNextPhrase()
        }else if(view.id in listOf(R.id.image_all,R.id.image_happy,R.id.image_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase(){
        val phrase = Mock().getPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

    private fun handleUsername(){
        val name:String= SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        binding.textUserName.text="Olá, $name!"
    }

    private fun handleFilter(id:Int){
        binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId=MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId=MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId=MotivationConstants.FILTER.SUNNY
            }
        }
    }

}