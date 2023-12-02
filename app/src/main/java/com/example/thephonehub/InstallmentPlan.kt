package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InstallmentPlan : AppCompatActivity() {

    //declare components
    lateinit var btn15 : Button
    lateinit var btn15pro : Button
    lateinit var btn15proMax : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installment_plan)

    //initialize component
    btn15 = findViewById<Button>(R.id.btn15)
    btn15pro = findViewById<Button>(R.id.btn15pro)
    btn15proMax = findViewById<Button>(R.id.btn15proMax)

    btn15.setOnClickListener {
        //connect to next page
        val i = Intent(this, iPhone15::class.java)

        //open session
        startActivity(i)
    }

        btn15pro.setOnClickListener {
            //connect to next page
            val i = Intent(this, iPhone15Pro::class.java)

            //open session
            startActivity(i)
        }

        btn15proMax.setOnClickListener {
            //connect to next page
            val i = Intent(this, iPhone15ProMax::class.java)

            //open session
            startActivity(i)
        }
    }
}