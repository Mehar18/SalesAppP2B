package com.example.salesappdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.adapter.CouponAdapter
import com.example.salesappdemo.adapter.LeadsAdapter
import com.example.salesappdemo.data.CouponDataBase
import com.example.salesappdemo.data.LeadDataBase
import com.example.salesappdemo.data.LeadsData
import com.example.salesappdemo.data.ModelLeadsDataClass
import com.google.firebase.database.*


class LeadsFragment : Fragment() {
    private lateinit var leadsArrayList  : ArrayList<LeadDataBase>
    private lateinit var dbRefernceLeads : DatabaseReference
    private lateinit var recyclerViewLeads : RecyclerView
    private lateinit var mAdapter:LeadsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leads, container, false)
        recyclerViewLeads = view.findViewById<RecyclerView>(R.id.recyclerViewLeads)
        recyclerViewLeads.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        mAdapter= LeadsAdapter()
        recyclerViewLeads.adapter = mAdapter

        leadsArrayList= arrayListOf<LeadDataBase>()
        getLeadsListData()

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE), Companion.PERMISSION_CODE
            )

        }

        val newRecordButton: Button = view.findViewById(R.id.newRecordBtn)
        newRecordButton.setOnClickListener {

            val fragment = LeadsAddRecordFragment()
            loadFragment(fragment)


        }

        return view
    }

    private fun getLeadsListData() {

        dbRefernceLeads = FirebaseDatabase.getInstance().getReference("Add Leads")
        dbRefernceLeads.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (codeSnap in snapshot.children){  //getting data from firebase - snapshot.children
                        val codeData = codeSnap.getValue(LeadDataBase::class.java)
                        leadsArrayList.add(codeData!!)

                    }
//                    val codeAdapter = CouponAdapter()
                    mAdapter = LeadsAdapter()
                    recyclerViewLeads.adapter = mAdapter
                    mAdapter.filterList(leadsArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                // couponArrayList.clear()

            }

        })

    }


//    private val leadsList = ArrayList<ModelLeadsDataClass>().apply {
//        add(ModelLeadsDataClass("name","mmmmm@gmail.com","9043410234",
//            "website","n/a","date","date","name"))
//
//    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        const val PERMISSION_CODE= 100
    }

}