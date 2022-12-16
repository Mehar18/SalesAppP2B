package com.example.salesappdemo

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.data.ModelCouponDataClass



class couponFragment : Fragment() {
//    lateinit var addRecordButton: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coupon, container, false)
        val recyclerViewCoupon = view.findViewById<RecyclerView>(R.id.recyclerViewCoupon)
        recyclerViewCoupon.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCoupon.adapter = couponAdapter(couponList)

        val addRecordButton : Button = view.findViewById(R.id.newrecordBtn)

        addRecordButton.setOnClickListener {
            val fragment = AddRecordCouponFragment()
            loadFragment(fragment)
        }


//        editButton = recyclerViewCoupon.findViewById(R.id.editBtnCoupon)
//
//
//        editButton.setOnClickListener {
//            val fragment = AddRecordCouponFragment()
//            loadFragment(fragment)
  //      }

        return view
    }





    private val couponList = ArrayList<ModelCouponDataClass>().apply{
        add(ModelCouponDataClass("DIVYA14","fixed",2000,
            "python certification and data structure ","vishwa priya",
            "1","Yes","",1))
        add(ModelCouponDataClass("DIVYA14","fixed",2000,
            "python certification and data structure ","vishwa priya",
            "1","Yes","",1))




    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

