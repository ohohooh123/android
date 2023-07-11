package com.jys.test31

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.opengl.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.scaleMatrix
import com.jys.test31.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var DataList = arrayListOf(
        Data(R.drawable.example, "0번"),
        Data(R.drawable.example,"1번"),
        Data(R.drawable.example,"2번"),
        Data(R.drawable.example,"3번")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listView.adapter = CustomAdapter(this, DataList)


//        binding.listView.onItemClickListener=AdapterView.OnItemClickListener{parent,view,position,id->
//            var selectItem = parent.getItemAtPosition(position)
//            Toast.makeText(this,selectItem.toString(),Toast.LENGTH_SHORT).show()
//        }




    }
}