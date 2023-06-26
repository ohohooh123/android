package com.jys.test30

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_SERVICE
import android.content.Intent
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.jys.test30.databinding.ActivityJavaBinding
import com.jys.test30.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityJavaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2= ActivityJavaBinding.inflate(layoutInflater)
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

        //불러오기
        var pref = this.getPreferences(0)
        binding.etEmail.setText(pref.getString("이메일", ""))
        binding.etPassword.setText(pref.getString("비밀번호", ""))
        binding.etName.setText(pref.getString("이름", ""))
        binding.etAge.setText(pref.getString("나이", ""))


    }

    fun Login(v: View) {
//        var imm = getSystemService(Context.INPUT_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(v.windowToken, 0)

        if (binding.etEmail.text.toString() == "abc@naver.com") {
            Toast.makeText(this, "성공!", Toast.LENGTH_SHORT).show()

            setContentView(binding2.root)
            binding2.tvResult.text = "연서님 환영합니다"

            var editor = this.getPreferences(0).edit()
            editor.putString("이메일", "abc@naver.com").apply()
            editor.putString("비밀번호", "1234").apply()
            editor.putString("이름", "YS").apply()
            editor.putString("나이", "30").apply()

        } else Toast.makeText(this, "실패ㅠㅠ", Toast.LENGTH_SHORT).show()
    }
}