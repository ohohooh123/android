package com.jys.firebasechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jys.firebasechat.databinding.ActivityChatListBinding
import com.jys.firebasechat.databinding.ActivityMainBinding
import com.jys.firebasechat.model.Room

class ChatListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatListBinding
    var database=Firebase.database("https://jyseofirst-default-rtdb.asia-southeast1.firebasedatabase.app")
    var roomsRef = database.getReference("rooms")
    companion object {
        var userId: String = ""
        var userName: String = ""
    }

    val roomList = mutableListOf<Room>()
    lateinit var adapter: ChatRoomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getStringExtra("userId") ?: "none"
        userName = intent.getStringExtra("userName") ?: "Anonymous"

        with(binding) {
            btnCreate.setOnClickListener { openCreateRoom() }
        }
        adapter = ChatRoomListAdapter(roomList)
        with(binding) {
            recycleRooms.adapter = adapter
            recycleRooms.layoutManager = LinearLayoutManager(baseContext)
        }
    loadRooms()
    }

    fun loadRooms() {
        roomsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                roomList.clear()
                for (item in snapshot.children) {
                    item.getValue(Room::class.java)?.let { room -> roomList.add(room) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })
    }

    fun openCreateRoom() {
        val editTitle = EditText(this)
        val dialog=AlertDialog.Builder(this).setTitle("방 이름").setView(editTitle).setPositiveButton("만들기"){
            dlg,id-> createRoom(editTitle.text.toString())
        }
        dialog.show()
    }
    fun createRoom(title: String) {
        val room = Room(title, userName)
        val roomId = roomsRef.push().key!!
        room.id = roomId
        roomsRef.child(roomId).setValue(room)
    }

    class ChatRoomListAdapter(val roomList: MutableList<Room>):RecyclerView.Adapter<ChatRoomListAdapter.Holder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Holder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder,position: Int) {
            val room=roomList.get(position)
            holder.setRoom(room)
        }

        override fun getItemCount(): Int {
            return roomList.size
        }
        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            lateinit var mRoom: Room
            init {
                itemView.setOnClickListener{
                    val intent=Intent(itemView.context,ChatRoomActivity::class.java)
                    intent.putExtra("roomId", mRoom.id)
                    intent.putExtra("roomTitle", mRoom.title)
                    itemView.context.startActivity(intent)
                }
            }
            fun setRoom(room: Room) {
                this.mRoom = room
                itemView.findViewById<TextView>(android.R.id.text1).setText(room.title)
            }
        }
    }
}