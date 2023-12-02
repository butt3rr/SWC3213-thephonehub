package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class ApplicationDetailsActivity : AppCompatActivity() {

    private lateinit var tvCustId: TextView
    private lateinit var tvCustName: TextView
    private lateinit var tvCustIc: TextView
    private lateinit var tvCustPhoneNum: TextView
    private lateinit var tvSalary: TextView
    private lateinit var tvDuration: TextView
    private lateinit var tvCustPhoneModel : TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_details)

        tvCustId = findViewById(R.id.tvCustId)
        tvCustName = findViewById(R.id.tvCustName)
        tvCustIc = findViewById(R.id.tvCustIc)
        tvCustPhoneNum = findViewById(R.id.tvCustPhoneNum)
        tvSalary = findViewById(R.id.tvSalary)
        tvDuration = findViewById(R.id.tvDuration)
        tvCustPhoneModel = findViewById(R.id.tvCustPhoneModel)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

        tvCustId.text = intent.getStringExtra("custID")
        tvCustName.text = intent.getStringExtra("custName")
        tvCustIc.text = intent.getStringExtra("custIc")
        tvCustPhoneNum.text = intent.getStringExtra("custPhoneNum")
        tvSalary.text = intent.getStringExtra("custSalary")
        tvDuration.text = intent.getStringExtra("dur")
        tvCustPhoneModel.text = intent.getStringExtra("custPhoneModel")

        btnDelete.setOnClickListener {
            deleteRecord(tvCustId.text.toString())
        }

        btnUpdate.setOnClickListener {
            openUpdateDialog(intent.getStringExtra("custID").toString(),
                intent.getStringExtra("custName").toString()    )
        }
    }

    private fun deleteRecord(id: String)
    {
        val dbRef = FirebaseDatabase.getInstance().getReference("Customer").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener { Toast.makeText(this, "Application Data Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, PurchaseHistory::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error -> Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun openUpdateDialog(custID: String, custName: String)
    {
        //dialog box
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etName = mDialogView.findViewById<EditText>(R.id.etName)
        val etIc = mDialogView.findViewById<EditText>(R.id.etIc)
        val etPhoneNum = mDialogView.findViewById<EditText>(R.id.etPhoneNum)
        val etSalary = mDialogView.findViewById<EditText>(R.id.etSalary)
        val etDuration = mDialogView.findViewById<EditText>(R.id.etDuration)
        val etPhoneModel = mDialogView.findViewById<EditText>(R.id.etPhoneModel)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etName.setText(intent.getStringExtra("custName").toString())
        etIc.setText(intent.getStringExtra("custIc").toString())
        etPhoneNum.setText(intent.getStringExtra("custPhoneNum").toString())
        etSalary.setText(intent.getStringExtra("custSalary").toString())
        etDuration.setText(intent.getStringExtra("dur").toString())
        etPhoneModel.setText(intent.getStringExtra("custPhoneModel").toString())

        mDialog.setTitle("Updating Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateCustData(
                custID,
                etName.text.toString(),
                etIc.text.toString(),
                etPhoneNum.text.toString(),
                etSalary.text.toString(),
                etDuration.text.toString(),
                etPhoneModel.text.toString(),

            )

            Toast.makeText(applicationContext, "Application Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews tvCustName.text = etCustName.text.toString()
            tvCustName.text = etName.text.toString()
            tvCustIc.text = etIc.text.toString()
            tvCustPhoneNum.text = etPhoneNum.text.toString()
            tvSalary.text = etSalary.text.toString()
            tvDuration.text = etDuration.text.toString()
            tvCustPhoneModel.text = etPhoneModel.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateCustData(id: String, name: String, ic: String, pNum: String, sal: String, dur: String, pModel: String)
    {
        val dbRef = FirebaseDatabase.getInstance().getReference("Customer").child(id)
        val custInfo = Model(id, name, ic, pNum, sal, dur, pModel)
        dbRef.setValue(custInfo)
        val intent = Intent(this, PurchaseHistory::class.java)
        finish()
        startActivity(intent)

    }
}