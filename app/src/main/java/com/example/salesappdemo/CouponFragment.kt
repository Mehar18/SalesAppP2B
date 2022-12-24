package com.example.salesappdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.data.CouponDataBase
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class CouponFragment : Fragment() {
//    lateinit var addRecordButton: Button
    private lateinit var couponArrayList  : ArrayList<CouponDataBase>
    private lateinit var dbRefernceCoupon : DatabaseReference
    private lateinit var recyclerViewCoupon : RecyclerView
    lateinit var edtImg : ImageView

    var codeString: String? = null
    lateinit var searchCoupon: SearchView
    private lateinit var madapter: CouponAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coupon, container, false)
        recyclerViewCoupon = view.findViewById<RecyclerView>(R.id.recyclerViewCoupon)
        recyclerViewCoupon.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewCoupon.adapter = CouponAdapter(couponList)
        madapter = CouponAdapter(couponList)
        couponArrayList = arrayListOf<CouponDataBase>()
        getCouponListDate()

        val addRecordButton : Button = view.findViewById(R.id.newrecordBtn)

        addRecordButton.setOnClickListener {
            val fragment = AddRecordCouponFragment()
            loadFragment(fragment)
        }

        searchCoupon = view.findViewById(R.id.searchCoupon)
        searchCoupon.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                couponFilter(query)
                return true


            }

            override fun onQueryTextChange(newText: String?): Boolean {

               // couponFilter(newText)
                Log.d("code",couponArrayList.toString())
                return false
            }

        })


        return view
    }

    private fun couponFilter(newText: String?) {
        val couponFilterList: ArrayList<CouponDataBase> = ArrayList()
        Log.d("cod",couponArrayList.toString())

        for (item in couponArrayList) {
            if (item.code.toLowerCase(Locale.ROOT).trim()
                    .contains(newText?.toLowerCase(Locale.ROOT)!!.trim())
            ) {


                couponFilterList.add(item)
                Log.d("co",item.toString())
                madapter.filterList(couponFilterList)

            }
//            madapter.filterList(couponFilterList)
        }
        if (couponFilterList.isEmpty()) {
        } else {
            madapter.filterList(couponArrayList)


        }
        madapter.filterList(couponFilterList)
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
               // couponArrayList.clear()

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
