package com.example.thephonehub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class PurchaseHistory : AppCompatActivity() {

    //declare all component
    private lateinit var dbRef: DatabaseReference
    private lateinit var appList: ArrayList<Model>
    private lateinit var btnM: Button
    private lateinit var aRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_history)

        btnM=findViewById<Button>(R.id.btnMenu)

        Toast.makeText(this, "Read Page", Toast.LENGTH_LONG).show()
        appList = arrayListOf<Model>()
        dbRef = FirebaseDatabase.getInstance().getReference("Customer")

        aRecyclerView = findViewById<RecyclerView>(R.id.rvAppHistory)
        aRecyclerView.layoutManager = LinearLayoutManager(this)

        aRecyclerView.visibility = View.VISIBLE

        //PUSH TO FIREBASE
        val custID = dbRef.push().key!!

        //val intent = Intent(this, ApplicationDetailsActivity::class.java)
        btnM.setOnClickListener {
            //declare variable i to connect to next pages
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        //panggil data dari db (datasnapshot)
        dbRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if(snapshot.exists())
                {
                    for(appSnap in snapshot.children)
                    {
                        var appData = appSnap.getValue(Model::class.java)
                        appList.add(appData!!)
                        Log.d("oneByone", appData.toString())
                    }

                    Log.d("TrackONE", appList.toString())
                    var mAdapter = Adapter(appList)
                    aRecyclerView.adapter = mAdapter
                    aRecyclerView.visibility = View.VISIBLE

                    mAdapter.setOnItemClickListener(object: Adapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@PurchaseHistory, ApplicationDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("custID", appList[position].custID)
                            intent.putExtra("custName", appList[position].custName)
                            intent.putExtra("custIc", appList[position].custIc)
                            intent.putExtra("custPhoneNum", appList[position].custPhoneNum)
                            intent.putExtra("custSalary", appList[position].custSalary)
                            intent.putExtra("dur", appList[position].dur)
                            intent.putExtra("custPhoneModel", appList[position].custPhoneModel)
                            startActivity(intent) //panggil in a group

                            Log.d("fbONEX", (appList[position].custID).toString())
                        }
                    })


                }
            }
            override fun onCancelled(error: DatabaseError){
                TODO("Not yet implemented")
            }
        })
    }
}