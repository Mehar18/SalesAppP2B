package com.example.salesappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.data.CouponDataBase
import com.google.firebase.database.*


class CouponFragment : Fragment() {
//    lateinit var addRecordButton: Button
    private lateinit var couponArrayList  : ArrayList<CouponDataBase>
    private lateinit var dbRefernceCoupon : DatabaseReference
    private lateinit var recyclerViewCoupon : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coupon, container, false)
        recyclerViewCoupon = view.findViewById<RecyclerView>(R.id.recyclerViewCoupon)
        recyclerViewCoupon.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCoupon.adapter = CouponAdapter(couponList)

        couponArrayList = arrayListOf<CouponDataBase>()
        getCouponListDate()

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

    private fun getCouponListDate() {

        dbRefernceCoupon = FirebaseDatabase.getInstance().getReference("Add coupon")
        dbRefernceCoupon.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (codeSnap in snapshot.children){  //getting data from firebase - snapshot.children
                        val codeData = codeSnap.getValue(CouponDataBase::class.java)
                        couponArrayList.add(codeData!!)

                    }
                    val codeAdapter = CouponAdapter(couponArrayList)
                    recyclerViewCoupon.adapter = codeAdapter

                }

            }

            override fun onCancelled(error: DatabaseError) {
                couponArrayList.clear()

            }

        })

    }


    private val couponList = ArrayList<CouponDataBase>().apply{
//        add(ModelCouponDataClass("DIVYA14","fixed",2000,
//            "python certification and data structure ","vishwa priya",
//            "1","Yes","",1))
//        add(ModelCouponDataClass("DIVYA14","fixed",2000,
//            "python certification and data structure ","vishwa priya",
//            "1","Yes","",1))




    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
