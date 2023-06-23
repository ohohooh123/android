package com.jys.test30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.jys.test30.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    var a = 50
    var flag = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etEmail.imeOptions = EditorInfo.IME_ACTION_SEND

        binding.etAge.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                Login(v)
                true
            } else {
                false
            }
        }

    }
    fun Login(v: View) {
        Toast.makeText(this,"성공!",Toast.LENGTH_SHORT).show()
    }

}