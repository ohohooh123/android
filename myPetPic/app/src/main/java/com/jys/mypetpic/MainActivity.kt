package com.jys.mypetpic

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.jys.mypetpic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var text1 : TextView
    lateinit var text2 : TextView
    lateinit var checkAgr : CheckBox
    lateinit var rGroup1: RadioGroup
    lateinit var rdoRabbit : RadioButton
    lateinit var rdoCat : RadioButton
    lateinit var rdoHorse : RadioButton
    lateinit var btnOK : Button
    lateinit var imgPet : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        title = "myPetPic"

        text1=binding.Text1
        checkAgr=binding.ChkAgree
        text2=binding.Text2
        rGroup1=binding.Rgroup1
        rdoRabbit=binding.RdoRabbit
        rdoCat=binding.RdoCat
        rdoHorse=binding.RdoHorse

        btnOK=binding.BtnOK
        imgPet=binding.ImgPet
        //text1=findViewById<TextView>(R.id.Text1)
        //checkAgr=findViewById<CheckBox>(R.id.ChkAgree)
        //text2 = findViewById<TextView>(R.id.Text2)
        //rGroup1=findViewById<RadioGroup>(R.id.Rgroup1)
        //rdoRabbit=findViewById<RadioButton>(R.id.RdoRabbit)
        //rdoCat=findViewById<RadioButton>(R.id.RdoCat)
        //rdoHorse=findViewById<RadioButton>(R.id.RdoHorse)
       // btnOK = findViewById<Button>(R.id.BtnOK)
        //imgPet=findViewById<ImageView>(R.id.ImgPet)

        checkAgr.setOnCheckedChangeListener { compoundButton, b ->
            if (checkAgr.isChecked) {
                text2.visibility = View.VISIBLE
                rGroup1.visibility = View.VISIBLE
                btnOK.visibility = View.VISIBLE
                imgPet.visibility = View.VISIBLE
            } else {
                text2.visibility = View.INVISIBLE
                rGroup1.visibility=View.INVISIBLE
                btnOK.visibility=View.INVISIBLE
                imgPet.visibility = View.INVISIBLE
            }
        }

        btnOK.setOnClickListener {
            when (rGroup1.checkedRadioButtonId) {
                R.id.RdoRabbit->imgPet.setImageResource(R.drawable.rabbit)
                R.id.RdoCat->imgPet.setImageResource(R.drawable.cat)
                R.id.RdoHorse->imgPet.setImageResource(R.drawable.horse)
                else -> Toast.makeText(applicationContext,"반려 동물을 먼저 선택하세요",Toast.LENGTH_SHORT).show()
            }
        }
    }
}