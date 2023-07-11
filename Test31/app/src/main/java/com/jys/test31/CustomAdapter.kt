package com.jys.test31

import android.content.Context
import android.graphics.drawable.DrawableContainer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.jys.test31.databinding.ActivityMainBinding
import com.jys.test31.databinding.CustomListBinding

class Data(val profile:Int, val name:String)

class CustomAdapter(val context:Context, val DataList:ArrayList<Data>) : BaseAdapter() {

    private var _binding: CustomListBinding? = null
    private val binding get() = _binding!!

    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CustomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getCount(): Int {
        return DataList.size
    }

    override fun getItem(position: Int): Any {
        return DataList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.custom_list, null)
        val profile = binding.ivCustom
        val name = binding.tvCustom
        val data = DataList[0]
        //아.. 이겝머냐?>??

        profile.setImageResource(data.profile)
        name.text = data.name
        return view

    }

}