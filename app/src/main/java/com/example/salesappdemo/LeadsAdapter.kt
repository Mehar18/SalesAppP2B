package com.example.salesappdemo

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.PackageManagerCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.data.ModelLeadsDataClass
import kotlinx.coroutines.NonDisposableHandle.parent
import java.security.AccessController


class LeadsAdapter(private val LeadList: ArrayList<ModelLeadsDataClass>) : RecyclerView.Adapter<LeadsAdapter.RowViewHolder>(){

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
                setHeaderBg(Nametxt)
                setHeaderBg(Emailtxt)
                setHeaderBg(MobileTxt)
                setHeaderBg(LeadSourceTxt)

                setHeaderBg(StatusTxt)
                setHeaderBg(CreatesAtTxt)
                setHeaderBg(UpdatedAtTxt)
                setHeaderBg(LeadownerTxt)
                setHeaderBg(editBtn)




                Nametxt.text = "Name"
                Emailtxt.text = "Email"
                MobileTxt.text = "Mobile"
                LeadSourceTxt.text = "Lead Source"

                StatusTxt.text = "Status"
                CreatesAtTxt.text = "Created at"
                UpdatedAtTxt.text = "Updated at"
                LeadownerTxt.text = "Lead owner"
                editBtn.setBackgroundResource(0)
            }
        } else {
            val modal = LeadList[rowPosition - 1]

            holder.apply {
                setContentBg(Nametxt)
                setContentBg(Emailtxt)
                setContentBg(MobileTxt)
                setContentBg(LeadSourceTxt)

                setContentBg(StatusTxt)
                setContentBg(CreatesAtTxt)
                setContentBg(UpdatedAtTxt)
                setContentBg(LeadownerTxt)

                Nametxt.text = modal.Name
                Emailtxt.text = modal.Email
                MobileTxt.text = modal.Mobile.toString()
                LeadSourceTxt.text = modal.LeadSource

                StatusTxt.text = modal.Status
                CreatesAtTxt.text = modal.createdAt
                UpdatedAtTxt.text = modal.updatedAt
                LeadownerTxt.text = modal.LeadOwner
            }
        }
        holder.editBtn.setOnClickListener {

            val activity = it.context as AppCompatActivity
            val fragment = LeadsNewRecordFRagment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit()


        }



        holder.MobileTxt.setOnClickListener {

            mobileString = holder.MobileTxt.text.toString()
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.setData(Uri.parse("tel:$mobileString"))
            startActivity(it.context, callIntent, null)


            val activity = it.context as AppCompatActivity
            val fragment = LeadsNewRecordFRagment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit()


        }




    }

    override fun getItemCount(): Int {
        return LeadList.size + 1 // one more to add header row
    }

    fun updateCoupon(update:ArrayList<ModelLeadsDataClass>){
        LeadList.clear()
        LeadList.addAll(update)
        notifyDataSetChanged()
    }


    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Nametxt: TextView = itemView.findViewById(R.id.txtName)
        val Emailtxt: TextView = itemView.findViewById(R.id.txtEmail)
        val MobileTxt: TextView = itemView.findViewById(R.id.txtMobile)
        val LeadSourceTxt: TextView = itemView.findViewById(R.id.LeadSource)
        val StatusTxt: TextView = itemView.findViewById(R.id.txtStatus)
        val CreatesAtTxt: TextView = itemView.findViewById(R.id.txtCreatedAt)
        val UpdatedAtTxt: TextView = itemView.findViewById(R.id.txtUpdatedAt)
        val LeadownerTxt: TextView = itemView.findViewById(R.id.txtLeadOwner)
        var editBtn : Button = itemView.findViewById(R.id.editBtnLead)

    }


    


}