package com.jys.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.jys.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val database = Firebase.database("https://jyseofirst-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef = database.getReference("users")
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun addItem(user: User) {
            val id = myRef.push().key!!
            user.id = id
            myRef.child(id).setValue(user)
        }

        with(binding) {
            btnPost.setOnClickListener {
                val name = editName.text.toString()
                val password = editPassword.text.toString()
                val user = User(name, password)
                addItem(user)
            }
        }
//        myRef.child("name").get().addOnSuccessListener {
//            Log.d("파이어베이스", "name=${it.value}")
//        }.addOnFailureListener {
//            Log.d("파이어베이스","error=${it}")
//        }

        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.textList.setText("")

                for (item in snapshot.children) {
                    item.getValue(User::class.java)?.let { user ->
                        binding.textList.append("${user.name} : ${user.password} \n")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })
    }
}

class User {
    var id: String = ""
    var name: String = ""
    var password: String = ""
    constructor()

    constructor(name: String, password: String){
        this.name = name
        this.password = password
    }
}
