package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class iPhone15Pro : AppCompatActivity() {

    //declare component
    lateinit var bC: Button
    lateinit var bR: Button
    lateinit var bA: Button
    lateinit var mPayment: TextView
    lateinit var dur: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iphone15_pro)

        bC = findViewById<Button>(R.id.btnCalculateIp15Pro)
        bR = findViewById<Button>(R.id.btnResetIp15Pro)
        bA = findViewById<Button>(R.id.btnApplyIp15Pro)
        mPayment = findViewById<TextView>(R.id.mPaymentIp15Pro)
        dur = findViewById<EditText>(R.id.editTextIp15Pro)

        //initialize function decimal
        val df = DecimalFormat("###.##")

        //declare fixed value (fixed price and interest)
        val price = 5499;
        val monthlyInterestRate = 1.2 / 100 / 12

        //function btnCalculateIp15
        bC.setOnClickListener {
            var durVal: Double = dur.text.toString().toDouble()

            var monthlyPayment = price * (monthlyInterestRate * Math.pow(1+ monthlyInterestRate, durVal)) / (Math.pow(1 + monthlyInterestRate, durVal)-1)


            mPayment.text = "You need to pay RM"+df.format(monthlyPayment).toString()+" per month"
        }

        //function btnResetIp15
        bR.setOnClickListener {
            //reset amount
            dur.setText(" ")

            mPayment.text = " "
        }

        //function connect page
        bA.setOnClickListener {
            //connect next page
            val i = Intent(this, InstallmentForm::class.java)

            //open session
            startActivity(i)
        }
    }
}