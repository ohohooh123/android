package com.jys.firebasechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.jys.firebasechat.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.jys.firebasechat.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val database = Firebase.database("https://jyseofirst-default-rtdb.asia-southeast1.firebasedatabase.app")
    val usersRef = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            binding.btnSignin.setOnClickListener { signin() }
            binding.btnSignup.setOnClickListener { signup() }
        }
    }

    fun signup() {
        with(binding) {
            val id = editId.text.toString()
            val password = editPassword.text.toString()
            val name = editName.text.toString()

            if (id.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                usersRef.child(id).get().addOnSuccessListener {
                    if (it.exists()) {
                        Toast.makeText(baseContext, "아이디가 존재합니다.", Toast.LENGTH_LONG).show()
                    } else {
                        val user = User(id, password, name)
                        usersRef.child(id).setValue(user)
                        signin()
                    }
                }
            } else {
                Toast.makeText(baseContext,"아이디, 비밀번호, 별명을 모두 입력해야 합니다.",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signin() {
        with(binding) {
            val id = editId.text.toString()
            val password = editPassword.text.toString()

            if (id.isNotEmpty() && password.isNotEmpty()) {
                usersRef.child(id).get().addOnSuccessListener {
                    if (it.exists()) {
                        it.getValue(User::class.java)?.let { user ->
                            if (user.password == password) {
                                goChatroomList(user.id, user.name)
                            } else {
                                Toast.makeText(baseContext, "비밀번호가 다릅니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(baseContext, "아이디가 없습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(baseContext,"아이디, 비밀번호를 입력해야 합니다.",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun goChatroomList(userId: String, userName: String) {
        val intent = Intent(this, ChatListActivity::class.java)

        intent.putExtra("userId", userId)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }
}


