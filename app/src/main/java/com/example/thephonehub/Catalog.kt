package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class Catalog : AppCompatActivity() {

    //declare components
    lateinit var btnA: Button
    lateinit var btnV: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        btnA = findViewById(R.id.btnApply)
        btnV = findViewById(R.id.btnView)

        btnA.setOnClickListener {
            //connect next page
            val i = Intent(this, InstallmentForm::class.java)

            //open session
            startActivity(i)
        }

        btnV.setOnClickListener {
            //connect next page
            val i = Intent(this, InstallmentPlan::class.java)

            //open session
            startActivity(i)
        }
    }
}