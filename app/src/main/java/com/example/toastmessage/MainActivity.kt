package com.example.toastmessage

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isDarkMode = false
    private var isReadMode = false

    @SuppressLint("SetTextI18n", "UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            val textView1=findViewById<TextView>(R.id.tV1)
            val textView2=findViewById<TextView>(R.id.tV2)
            textView1.text="""
                System Insests
                Left : ${systemBars.left} px
                Top : ${systemBars.top} px
                Right : ${systemBars.right} px
                Bottom : ${systemBars.bottom} px
                $systemBars
            """.trimIndent()

            textView2.text=systemBars.toString()
            insets
        }

        val buttonDark=findViewById<Button>(R.id.btnDark)
        val buttonRead=findViewById<Button>(R.id.btnRead)
        val bg=findViewById<LinearLayout>(R.id.main)

        //dark mode button logic
        buttonDark.setOnClickListener {
            if(isDarkMode){
                bg.setBackgroundResource(R.color.white)
                Toast.makeText(this,"Original Mode",Toast.LENGTH_SHORT).show()
            }else{
                bg.setBackgroundResource(R.color.black)
                Toast.makeText(this,"Dark Mode",Toast.LENGTH_SHORT).show()
            }
            isDarkMode=!isDarkMode
            isReadMode=false  //reset read mode
        }

        //read mode button logic
        buttonRead.setOnClickListener {
            if(isReadMode){
                bg.setBackgroundResource(R.color.white)
                Toast.makeText(this,"Original Mode",Toast.LENGTH_SHORT).show()
            }else{
                bg.setBackgroundResource(R.color.yellow)
                Toast.makeText(this,"Read Mode",Toast.LENGTH_SHORT).show()
            }
            isReadMode=!isReadMode
            isDarkMode=false  //reset dark mode
        }

        val buttonNext=findViewById<Button>(R.id.btnNext) //button for going to MainActivity2
        buttonNext.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        val buttonCamera=findViewById<CardView>(R.id.cardCamera) //button for opening camera
        buttonCamera.setOnClickListener {
            val intentCamera=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intentCamera)
        }

        val buttonGoogle=findViewById<CardView>(R.id.cardGoogle) //button for opening google
        buttonGoogle.setOnClickListener {
            val intentGoogle=Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com"))
            startActivity(intentGoogle)
        }


    }
}