package com.jys.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jys.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        binding.listView.onItemClickListener=AdapterView.OnItemClickListener { parent, view, position, id ->
//            val selectItem = parent.getItemAtPosition(position)
//            Toast.makeText(this,selectItem.toString(),Toast.LENGTH_SHORT).show()
//        }
    }
}