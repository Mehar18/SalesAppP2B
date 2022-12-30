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
import com.example.salesappdemo.LeadsNewRecordFragment
import com.example.salesappdemo.R
import com.example.salesappdemo.data.ModelLeadsDataClass


class LeadsAdapter(private val leadList: ArrayList<ModelLeadsDataClass>) : RecyclerView.Adapter<LeadsAdapter.RowViewHolder>(){

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
        val rowPosition = holder.adapterPosition

        if (rowPosition == 0) {
            // Header Cells. Main Headings appear here
            holder.apply {
                setHeaderBg(nameTxt)
                setHeaderBg(emailTxt)
                setHeaderBg(mobileTxt)
                setHeaderBg(leadSourceTxt)

                setHeaderBg(statusTxt)
                setHeaderBg(createsAtTxt)
                setHeaderBg(updatedAtTxt)
                setHeaderBg(leadOwnerTxt)
                setHeaderBg(editBtn)




                nameTxt.text = "Name"
                emailTxt.text = "Email"
                mobileTxt.text = "Mobile"
                leadSourceTxt.text = "Lead Source"

                statusTxt.text = "Status"
                createsAtTxt.text = "Created at"
                updatedAtTxt.text = "Updated at"
                leadOwnerTxt.text = "Lead owner"
                editBtn.setBackgroundResource(0)
            }
        } else {
            val modal = leadList[rowPosition - 1]

            holder.apply {
                setContentBg(nameTxt)
                setContentBg(emailTxt)
                setContentBg(mobileTxt)
                setContentBg(leadSourceTxt)

                setContentBg(statusTxt)
                setContentBg(createsAtTxt)
                setContentBg(updatedAtTxt)
                setContentBg(leadOwnerTxt)

                nameTxt.text = modal.Name
                emailTxt.text = modal.Email
                mobileTxt.text = modal.Mobile.toString()
                leadSourceTxt.text = modal.LeadSource

                statusTxt.text = modal.Status
                createsAtTxt.text = modal.createdAt
                updatedAtTxt.text = modal.updatedAt
                leadOwnerTxt.text = modal.LeadOwner
            }
        }
        holder.editBtn.setOnClickListener {

            val activity = it.context as AppCompatActivity
            val fragment = LeadsNewRecordFragment()

            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit()

        }



        holder.mobileTxt.setOnClickListener {

            mobileString = holder.mobileTxt.text.toString()
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:$mobileString"))
            startActivity(it.context, callIntent, null)


            val activity = it.context as AppCompatActivity
            val fragment = LeadsNewRecordFragment()
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
        return leadList.size + 1 // one more to add header row
    }

    fun updateCoupon(update:ArrayList<ModelLeadsDataClass>){
        leadList.clear()
        leadList.addAll(update)
        notifyDataSetChanged()
    }


    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.txtName)
        val emailTxt: TextView = itemView.findViewById(R.id.txtEmail)
        val mobileTxt: TextView = itemView.findViewById(R.id.txtMobile)
        val leadSourceTxt: TextView = itemView.findViewById(R.id.leadSource)
        val statusTxt: TextView = itemView.findViewById(R.id.txtStatus)
        val createsAtTxt: TextView = itemView.findViewById(R.id.txtCreatedAt)
        val updatedAtTxt: TextView = itemView.findViewById(R.id.txtUpdatedAt)
        val leadOwnerTxt: TextView = itemView.findViewById(R.id.txtLeadOwner)
        var editBtn : Button = itemView.findViewById(R.id.editBtnLead)

    }


    


}