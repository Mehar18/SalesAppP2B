package com.example.salesappdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.data.CouponDataBase


class CouponAdapter() :RecyclerView.Adapter<CouponAdapter.RowViewHolder>() {
  //  private val couponList: ArrayList<ModelCouponDataClass> = ArrayList()
  private var couponList: ArrayList<CouponDataBase> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.coupon_table_list, parent, false)
        return RowViewHolder(itemView)
    }





//    private fun setHeaderBg(view: View) {
//        view.setBackgroundResource(R.drawable.table_header_cell_bg)
//    }
//
//    private fun setContentBg(view: View) {
//        view.setBackgroundResource(R.drawable.table_content_cell_bg)
//    }

    override fun onBindViewHolder(holder: RowViewHolder, position: Int) {
        val currentPosition = couponList[position]
        holder.txtc.text = currentPosition.code
        holder.txtT.text = currentPosition.type
        holder.txtA.text = currentPosition.amount.toString()
        holder.txtcourse.text = currentPosition.course
        holder.txtEmployee.text=currentPosition.employee
        holder.editImg.setOnClickListener {

            val activity = it.context as AppCompatActivity
            val fragment = AddRecordCouponFragment()
            activity.supportFragmentManager.beginTransaction().
            replace(R.id.container,fragment).addToBackStack(null).commit()



        }
        holder.imgThumbStatus.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24)
        holder.imgThumbStatus.setTag(R.drawable.ic_baseline_thumb_up_alt_24)

        holder.imgThumbStatus.setOnClickListener{
            if (holder.imgThumbStatus.getTag() ==R.drawable.ic_baseline_thumb_up_alt_24){
                holder.imgThumbStatus.setImageResource(R.drawable.ic_baseline_thumb_down_alt_24)
                holder.imgThumbStatus.setTag(R.drawable.ic_baseline_thumb_down_alt_24)
            }else{
                holder.imgThumbStatus.setImageResource(R.drawable.ic_baseline_thumb_up_alt_24)
                holder.imgThumbStatus.setTag(R.drawable.ic_baseline_thumb_up_alt_24)
            }
        }


    }

    override fun getItemCount(): Int {
        return couponList.size // one more to add header row
    }

    fun filterList(filterlist: ArrayList<CouponDataBase>) {

        this.couponList = filterlist
        notifyDataSetChanged()
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
        val imgThumbStatus: ImageView = itemView.findViewById(R.id.StatusCoupon)
        val txtMaxUsage: TextView = itemView.findViewById(R.id.txtMaxUsage)
        var editImg : ImageView = itemView.findViewById(R.id.editImgCoupon)

    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

//    private fun loadFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.container, fragment)
//            addToBackStack(null)
//            commit()
//        }
//    }



}