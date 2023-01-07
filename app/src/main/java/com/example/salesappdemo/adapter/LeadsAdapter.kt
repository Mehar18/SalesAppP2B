package com.example.salesappdemo.adapter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.LeadsEditableFragment
import com.example.salesappdemo.R
import com.example.salesappdemo.data.CouponDataBase
import com.example.salesappdemo.data.LeadDataBase
import com.example.salesappdemo.data.ModelLeadsDataClass


class LeadsAdapter() : RecyclerView.Adapter<LeadsAdapter.RowViewHolder>(){

   private var leadList: ArrayList<LeadDataBase> = ArrayList()

    lateinit var mobileString: String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.leads_table_list, parent, false)
        return RowViewHolder(itemView)
    }

    private fun setHeaderBg(view: View) {
        view.setBackgroundResource(R.drawable.table_header_cell_bg)
    }

    private fun setContentBg(view: View) {
        view.setBackgroundResource(R.drawable.table_content_cell_bg)
    }


    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val leadsCurrentPosition = leadList[position]
        holder.nameTxt.text = leadsCurrentPosition.name
        holder.emailTxt.text = leadsCurrentPosition.email
        holder.mobileTxt.text=leadsCurrentPosition.mobile
        holder.leadSourceTxt.text=leadsCurrentPosition.leadSource
        holder.statusTxt.text=leadsCurrentPosition.status
        holder.createsAtTxt.text=leadsCurrentPosition.createdAt
        holder.updatedAtTxt.text=leadsCurrentPosition.updatedAt
        holder.leadOwnerTxt.text=leadsCurrentPosition.leadOwner



//        holder.editBtn.setOnClickListener {
//
//            val activity = it.context as AppCompatActivity
//            val fragment = LeadsEditableFragment()
//
//            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
//                .addToBackStack(null).commit()
//
//        }



        holder.mobileTxt.setOnClickListener {

            mobileString = holder.mobileTxt.text.toString()
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:$mobileString"))
            startActivity(it.context, callIntent, null)


            val activity = it.context as AppCompatActivity
            val fragment = LeadsEditableFragment()
            val bundle = Bundle()
            bundle.putString("name",holder.nameTxt.text.toString())
            bundle.putString("email",holder.emailTxt.text.toString())
            bundle.putString("mobileNumber",holder.mobileTxt.text.toString())
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit()

        }




    }

    override fun getItemCount(): Int {
        return leadList.size // one more to add header row
    }

    fun filterList(filterlist: ArrayList<LeadDataBase>) {

        this.leadList = filterlist
        notifyDataSetChanged()
    }

//    fun updateCoupon(update:ArrayList<ModelLeadsDataClass>){
//        leadList.clear()
//        leadList.addAll(update)
//        notifyDataSetChanged()
//    }


    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.txtName)
        val emailTxt: TextView = itemView.findViewById(R.id.txtEmail)
        val mobileTxt: TextView = itemView.findViewById(R.id.txtMobile)
        val leadSourceTxt: TextView = itemView.findViewById(R.id.leadSource)
        val statusTxt: TextView = itemView.findViewById(R.id.txtStatus)
        val createsAtTxt: TextView = itemView.findViewById(R.id.txtCreatedAt)
        val updatedAtTxt: TextView = itemView.findViewById(R.id.txtUpdatedAt)
        val leadOwnerTxt: TextView = itemView.findViewById(R.id.txtLeadOwner)
//        var editBtn : Button = itemView.findViewById(R.id.editBtnLead)

    }


    


}