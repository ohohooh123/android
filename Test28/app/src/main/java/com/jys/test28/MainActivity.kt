package com.jys.test28

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val a = 1
    var b = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        println("안녕안녕")
        b = 3

        val str = "코로나 조심하세요"
        val str2 = str.replace("조심하세요", "바보")
        println(str)
        println(str2)

    }
}