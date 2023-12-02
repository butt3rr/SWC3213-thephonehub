package com.example.thephonehub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager.AccessibilityServicesStateChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.FieldPosition

//masukkan data dlm recyclerview
class Adapter (private val appList: ArrayList<Model>):
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    //app_list_item is refer to layout named emp_ist_item
    //design file refer to app_list_item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.app_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    //component that had layout app_list_item
    //custName: edit text, custIc: edit text, custPhoneNum: edit text, custSalary: edit text, dur: edit text, custPhoneModel: edit text
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentApp = appList[position]
        //baca data dari firebase
        holder.tvCustName.text = currentApp.custName
        holder.tvCustIc.text = currentApp.custIc
        holder.tvCustPhoneNum.text = currentApp.custPhoneNum
        holder.tvCustSalary.text = currentApp.custSalary
        holder.tvDuration.text = currentApp.dur
        holder.tvCustPhoneModel.text = currentApp.custPhoneModel
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvCustName : TextView = itemView.findViewById(R.id.tvCustName)
        val tvCustIc : TextView = itemView.findViewById(R.id.tvCustIc)
        val tvCustPhoneNum : TextView = itemView.findViewById(R.id.tvCustPhoneNum)
        val tvCustSalary : TextView = itemView.findViewById(R.id.tvCustSalary)
        val tvDuration : TextView = itemView.findViewById(R.id.tvDuration)
        val tvCustPhoneModel : TextView = itemView.findViewById(R.id.tvCustPhoneModel)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            } }
    }

}