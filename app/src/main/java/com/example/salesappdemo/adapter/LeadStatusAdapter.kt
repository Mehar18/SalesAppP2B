package com.example.salesappdemo.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.LeadStatusEditFragment
import com.example.salesappdemo.R
import com.example.salesappdemo.data.LeadStatusData

class LeadStatusAdapter(private val list: ArrayList<LeadStatusData>):RecyclerView.Adapter<LeadStatusAdapter.LeadStatusViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadStatusViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lead_status_list, parent, false)
        return LeadStatusViewHolder(itemView)
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundColor(Color.parseColor("#90E4DFDF"))
    }

    override fun onBindViewHolder(holder: LeadStatusViewHolder, position: Int) {
       val current = list[position]
//        if (position%2==0){
//            setHeaderBg(holder.nameEdt)
//            setHeaderBg(holder.emailIdEdt)
//            setHeaderBg(holder.mobileNumberEdt)
//            setHeaderBg(holder.leadStatusEdt)
//            setHeaderBg(holder.leadSourceEdt)
//            setHeaderBg(holder.followUpDateEdt)
//            setHeaderBg(holder.paymentReceiptEdt)
//            setHeaderBg(holder.assignedAtEdt)
//            setHeaderBg(holder.assignedToEdt)
//            setHeaderBg(holder.latestUpdateEdt)
//            setHeaderBg(holder.paymentReceiptEdt)
//            setHeaderBg(holder.imageEdit)
//        }
        if (position%2==0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#90E4DFDF"))
        }
        holder.nameEdt.text = current.name
        holder.emailIdEdt.text = current.email
        holder.mobileNumberEdt.text = current.mobile
        holder.leadSourceEdt.text = current.leadSource
        holder.leadStatusEdt.text = current.leadStatus
        holder.followUpDateEdt.text = current.followUpDate
        holder.paymentReceiptEdt.text = current.paymentReceipt
        holder.assignedToEdt.text = current.assignedTo
        holder.latestUpdateEdt.text = current.latestUpdate
        holder.assignedAtEdt.text = current.assignedAt
        holder.imageEdit.setImageResource(R.drawable.ic_baseline_edit_24)

        holder.imageEdit.setOnClickListener {

            val activity = it.context as AppCompatActivity
            val fragment = LeadStatusEditFragment()

            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit()

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class LeadStatusViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val nameEdt : TextView = itemView.findViewById(R.id.name)
        val emailIdEdt :TextView = itemView.findViewById(R.id.emailId)
        val mobileNumberEdt : TextView = itemView.findViewById(R.id.mobileNo)
        val leadSourceEdt :TextView = itemView.findViewById(R.id.leadSource)
        val leadStatusEdt :TextView = itemView.findViewById(R.id.leadStatus)
        val followUpDateEdt :TextView = itemView.findViewById(R.id.followedUpDate)
        val paymentReceiptEdt: TextView = itemView.findViewById(R.id.paymentReceipt)
        val assignedToEdt:TextView = itemView.findViewById(R.id.assignedTo)
        val latestUpdateEdt :TextView = itemView.findViewById(R.id.latestUpdated)
        val assignedAtEdt :TextView = itemView.findViewById(R.id.assignedAt)
        val imageEdit: ImageView = itemView.findViewById(R.id.editImgCoupon)



    }

}