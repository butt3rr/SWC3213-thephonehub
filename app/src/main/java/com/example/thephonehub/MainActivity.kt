package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //declare components
    lateinit var btnA: Button
    lateinit var btnB: Button
    lateinit var btnC: Button
    lateinit var btnD: Button
    lateinit var btnE: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize component
        btnA = findViewById<Button>(R.id.btnAboutUs)
        btnB = findViewById<Button>(R.id.btnCalculator)
        btnC = findViewById<Button>(R.id.btnInstallmentForm)
        btnD = findViewById<Button>(R.id.btnCatalog)
        btnE = findViewById<Button>(R.id.btnHistory)

        btnA.setOnClickListener {
            //connect next page
            val h = Intent(this, AboutUs::class.java)

            //open session
            startActivity(h)
        }

        btnB.setOnClickListener {
            //connect next page
            val i = Intent(this, InstallmentPlan::class.java)

            //open session
            startActivity(i)
        }

        btnC.setOnClickListener {
            //connect next page
            val j = Intent(this, InstallmentForm::class.java)

            //open session
            startActivity(j)
        }

        btnD.setOnClickListener {
            //connect next page
            val k = Intent(this, Catalog::class.java)

            //open session
            startActivity(k)
        }

        btnE.setOnClickListener {
            //connect next page
            val l = Intent(this, PurchaseHistory::class.java)

            //open session
            startActivity(l)
        }
    }
}