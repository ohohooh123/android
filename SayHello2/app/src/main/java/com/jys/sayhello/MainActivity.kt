package com.jys.sayhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jys.sayhello.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.btnSay.setOnClickListener{
            binding.textSay.text="Hello Kotlin!!"
        }
    }
}