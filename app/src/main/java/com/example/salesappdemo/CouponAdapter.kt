package com.example.salesappdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.data.CouponDataBase


class CouponAdapter (private val couponList: ArrayList<CouponDataBase>) :RecyclerView.Adapter<CouponAdapter.RowViewHolder>() {
  //  private val couponList: ArrayList<ModelCouponDataClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.coupon_table_list, parent, false)
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
                setHeaderBg(txtA)
                setHeaderBg(txtT)
                setHeaderBg(txtc)
                setHeaderBg(txtcourse)

                setHeaderBg(txtEmployee)
                setHeaderBg(txtAddedBy)
                setHeaderBg(txtUsed)
                setHeaderBg(txtStatus)
                setHeaderBg(txtMaxUsage)




                txtA.text = "Amount"
                txtT.text = "Type"
                txtc.text = "coupon"
                txtcourse.text = "Course"

                txtEmployee.text = "Employee"
                txtAddedBy.text = "Added By"
                txtUsed.text = "Used"
                txtStatus.text = "Status"
                txtMaxUsage.text = "Max Usage"
                editBtn.setBackgroundResource(0)
            }
        } else {
            val modal = couponList[rowPosition - 1]

            holder.apply {
                setContentBg(txtc)
                setContentBg(txtA)
                setContentBg(txtcourse)
                setContentBg(txtT)

                setContentBg(txtEmployee)
                setContentBg(txtAddedBy)
                setContentBg(txtUsed)
                setContentBg(txtStatus)
                setContentBg(txtMaxUsage)

                txtc.text = modal.code
                txtT.text = modal.type
                txtA.text = modal.amount.toString()
                txtcourse.text = modal.course

                txtEmployee.text = modal.employee
                txtAddedBy.text = ""
                txtUsed.text = ""
                txtStatus.text = ""
                txtMaxUsage.text = "0"
            }
        }

        holder.editBtn.setOnClickListener {

            val activity = it.context as AppCompatActivity
            val fragment = AddRecordCouponFragment()
            activity.supportFragmentManager.beginTransaction().
            replace(R.id.container,fragment).addToBackStack(null).commit()


        }


    }

    override fun getItemCount(): Int {
        return couponList.size + 1 // one more to add header row
    }

//    fun updateCoupon(update:ArrayList<ModelCouponDataClass>){
//        couponList.clear()
//        couponList.addAll(update)
//        notifyDataSetChanged()
//    }



    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtc: TextView = itemView.findViewById(R.id.txtCoupon)
        val txtT: TextView = itemView.findViewById(R.id.txtCouponType)
        val txtcourse: TextView = itemView.findViewById(R.id.CourseCoupon)
        val txtA: TextView = itemView.findViewById(R.id.txtAmountCoupon)
        val txtEmployee: TextView = itemView.findViewById(R.id.txtEmployeeCoupon)
        val txtAddedBy: TextView = itemView.findViewById(R.id.txtAddedByCoupon)
        val txtUsed: TextView = itemView.findViewById(R.id.txtUsedCoupon)
        val txtStatus: TextView = itemView.findViewById(R.id.StatusCoupon)
        val txtMaxUsage: TextView = itemView.findViewById(R.id.txtMaxUsage)
        var editBtn : Button = itemView.findViewById(R.id.editBtnCoupon)

    }

//    private fun loadFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.container, fragment)
//            addToBackStack(null)
//            commit()
//        }
//    }



}