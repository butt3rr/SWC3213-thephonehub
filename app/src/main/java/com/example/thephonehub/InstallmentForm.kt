package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InstallmentForm : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnS : Button
    private lateinit var btnR : Button
    private lateinit var name: EditText
    private lateinit var icNumber : EditText
    private lateinit var phoneNum: EditText
    private lateinit var monthlySalary: EditText
    private lateinit var duration : EditText
    private lateinit var phoneModel: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installment_form)

        //declare the component
        btnS = findViewById<Button>(R.id.btnSubmit)
        btnR = findViewById<Button>(R.id.btnResetForm)
        name = findViewById<EditText>(R.id.name)
        icNumber = findViewById<EditText>(R.id.icNumber)
        phoneNum = findViewById<EditText>(R.id.phoneNum)
        monthlySalary = findViewById<EditText>(R.id.monthlySalary)
        duration = findViewById<EditText>(R.id.duration)
        phoneModel = findViewById<EditText>(R.id.phoneModel)

        //popup message when click button add record
        Toast.makeText(this, "Fill In The Form", Toast.LENGTH_LONG).show()

        btnS.setOnClickListener{
            //call function save CustData
            //parameter - change the input data to string
            saveCustData(name.text.toString(),icNumber.text.toString(), phoneNum.text.toString(),
                monthlySalary.text.toString(), duration.text.toString(), phoneModel.text.toString())

        }

        btnR.setOnClickListener {
            name.setText(" ")
            icNumber.setText(" ")
            phoneNum.setText(" ")
            monthlySalary.setText(" ")
            duration.setText(" ")
            phoneModel.setText(" ")
        }
    }

    //create the function saveEmployeeData
    //this function send data to fire base
    //n = name, ic = ic number, pn = phone number, ms = monthly salary, d = duration, pm = phone model

    private fun saveCustData(n:String, ic:String, pn:String, ms:String, d:String, pm:String)
    {
        //getInstance = get object
        //Customer refer to table
        //link to database named Customer
        dbRef = FirebaseDatabase.getInstance().getReference("Customer")

        //produce auto generate cust id
        //!! refer must had record or id cannot null
        val custId = dbRef.push().key!!

        //cm is a object
        //push the data to database
        //empId will autogenerate
        //data will output by user
        //input name and age
        val cm = Model(custId, n, ic, pn, ms, d, pm )

        //setting to push data inside table
        dbRef.child(custId).setValue(cm)

            //success record it will pop up success
            .addOnCompleteListener{
                Toast.makeText(this,"Thank you! We will contact you if you're qualified", Toast.LENGTH_LONG).show()
                //fail to record it will popup failure
            }.addOnFailureListener{
                Toast.makeText(this,"Fail to fill form", Toast.LENGTH_LONG).show()
            }

        //declare variable i to connect to next pages/ activity
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}