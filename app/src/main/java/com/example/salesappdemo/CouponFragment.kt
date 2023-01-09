package com.example.salesappdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.adapter.CouponAdapter
import com.example.salesappdemo.data.CouponDataBase
import com.google.firebase.database.*
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList


class CouponFragment : Fragment() {
//    lateinit var addRecordButton: Button
    private lateinit var couponArrayList  : ArrayList<CouponDataBase>
    private lateinit var dbRefernceCoupon : DatabaseReference
    private lateinit var recyclerViewCoupon : RecyclerView
    private lateinit var couponCount:TextView
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
        recyclerViewCoupon.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        madapter = CouponAdapter()
        recyclerViewCoupon.adapter = madapter

        couponArrayList = arrayListOf<CouponDataBase>()
        getCouponListData()

        couponCount = view.findViewById(R.id.couponCount)




        val addRecordButton : Button = view.findViewById(R.id.newrecordBtn)

        addRecordButton.setOnClickListener {
            val fragment = AddRecordCouponFragment()
            loadFragment(fragment)
        }

        searchCoupon = view.findViewById(R.id.searchCoupon)
        searchCoupon.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//                couponFilter(query)
                return false


            }

            override fun onQueryTextChange(newText: String?): Boolean {

                couponFilter(newText)
              //  Log.d("code",couponArrayList.toString())
                return true
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
              //  Log.d("co",item.toString())
                madapter.filterList(couponFilterList)

            }
//            madapter.filterList(couponFilterList)
        }
//        }
        madapter.filterList(couponFilterList)
    }



    private fun getCouponListData() {

        dbRefernceCoupon = FirebaseDatabase.getInstance().getReference("Add coupon")
        dbRefernceCoupon.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (codeSnap in snapshot.children){  //getting data from firebase - snapshot.children
                        val codeData = codeSnap.getValue(CouponDataBase::class.java)
                        couponArrayList.add(codeData!!)

                    }
//                    val codeAdapter = CouponAdapter()
                    madapter = CouponAdapter()
                    recyclerViewCoupon.adapter = madapter
                    madapter.filterList(couponArrayList)


                }


                couponCount.setText("[Total ${couponArrayList.size} records found]")


            }

            override fun onCancelled(error: DatabaseError) {
               // couponArrayList.clear()

            }

        })

    }


    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
