package com.jys.firebasechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.jys.firebasechat.databinding.ActivityChatRoomBinding
import com.jys.firebasechat.databinding.ItemMsgListBinding
import com.jys.firebasechat.model.Message


class ChatRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatRoomBinding
    var database= Firebase.database("https://jyseofirst-default-rtdb.asia-southeast1.firebasedatabase.app")
    lateinit var msgRef: DatabaseReference

    var roomId: String = ""
    var roomTitle: String = ""

    val msgList = mutableListOf<Message>()
    lateinit var adapter: MsgListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //3 4 6
        roomId = intent.getStringExtra("roomId") ?: "none"
        roomTitle = intent.getStringExtra("roomTigle") ?: "없음"

        msgRef = database.getReference("rooms").child(roomId).child("messages")

        adapter = MsgListAdapter(msgList)
        with(binding) {
            recycleMsgs.adapter = adapter
            recycleMsgs.layoutManager = LinearLayoutManager(baseContext)
            //9

            textTitle.setText(roomTitle)
            btnBack.setOnClickListener{ finish()}
            btnSend.setOnClickListener{ sendMsg()}
        }
        loadMsg()
    }

    // 5 8
    fun loadMsg() {
        msgRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                msgList.clear()
                for (item in snapshot.children) {
                    item.getValue(Message::class.java)?.let { msg -> msgList.add(msg) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                print(error.message)
            }
        })
    }
    fun sendMsg() {
        with(binding) {
            if (editMsg.text.isNotEmpty()) {
                val message = Message(editMsg.text.toString(), ChatListActivity.userName)
                val msgId = msgRef.push().key!!
                message.id=msgId
                msgRef.child(msgId).setValue(message)
                editMsg.setText("")
            }
        }
    }
}

class MsgListAdapter(val msgList: MutableList<Message>) :
    RecyclerView.Adapter<MsgListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMsgListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder:Holder, position: Int) {
        val msg = msgList.get(position)
        holder.setMsg(msg)
    }

    override fun getItemCount(): Int {
        return msgList.size
    }
    class Holder(val binding: ItemMsgListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setMsg(msg: Message) {
            binding.textName.setText(msg.userName)
            binding.textMsg.setText(msg.msg)
            binding.textDate.setText("${msg.timestamp}")
        }
    }
}
