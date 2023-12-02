package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutUs : AppCompatActivity() {

    //declare component
    lateinit var btnA: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        btnA = findViewById(R.id.btnMainMenu)

        btnA.setOnClickListener {
            //connect next page
            val i = Intent(this, MainActivity::class.java)

            //open session
            startActivity(i)
        }
    }
}