package com.example.salesappdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
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
import java.util.*
import kotlin.collections.ArrayList


class LeadsFragment : Fragment() {
    private lateinit var leadsArrayList  : ArrayList<LeadDataBase>
    private lateinit var dbRefernceLeads : DatabaseReference
    private lateinit var recyclerViewLeads : RecyclerView
    private lateinit var mAdapter:LeadsAdapter
    val selectStatusArrayList: ArrayList<String> = ArrayList()
    lateinit var selectedLeadStatusString:String
    val selectSourceArrayList: ArrayList<String> = ArrayList()
    lateinit var selectedLeadSourceString:String


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
        selectStatusArrayList.add("All")
        selectStatusArrayList.add("--N/A--")
        selectStatusArrayList.add("Busy")
        selectStatusArrayList.add("Closed")
        selectStatusArrayList.add("Connected")
        selectStatusArrayList.add("Follow up")
        selectStatusArrayList.add("Not interested")
        selectStatusArrayList.add("Not reachable")
        selectStatusArrayList.add("Paid")
        selectStatusArrayList.add("Ringing")
        selectStatusArrayList.add("Switch off")
        selectStatusArrayList.add("Wrong number")

        val selectStatusAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,selectStatusArrayList
        )
        val selectStatusSpinner: Spinner = view.findViewById(R.id.leadStatusSpinner1)
        selectStatusSpinner.setAdapter(selectStatusAdapter)
        selectStatusSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedLeadStatusString = selectedItem
                if (selectedLeadStatusString.equals("All")){

                }else {
                    leadsStatusFilter(selectedLeadStatusString)
                }




            }
        }

        selectSourceArrayList.add("All")
        selectSourceArrayList.add("Website")
        selectSourceArrayList.add("Webinar")
        selectSourceArrayList.add("Social Media")

        val selectSourceAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,selectSourceArrayList
        )
        val selectSourceSpinner: Spinner = view.findViewById(R.id.leadSourceSpinner1)
        selectSourceSpinner.setAdapter(selectSourceAdapter)
        selectSourceSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedLeadSourceString = selectedItem

                if (selectedLeadSourceString.equals("All")){

                }else {
                    leadsSourceFilter(selectedLeadSourceString)
                }


            }
        }





        return view
    }

    private fun leadsStatusFilter(newText: String?) {
        val statusFilterList: ArrayList<LeadDataBase> = ArrayList()
       // Log.d("cod",couponArrayList.toString())

        for (item in leadsArrayList) {
            if (item.status.toLowerCase(Locale.ROOT).trim()
                    .contains(newText?.toLowerCase(Locale.ROOT)!!.trim())
            ) {
                statusFilterList.add(item)
                //  Log.d("co",item.toString())
                mAdapter.filterList(statusFilterList)

            }
//            madapter.filterList(couponFilterList)
        }
//        }
        mAdapter.filterList(statusFilterList)
    }

    private fun leadsSourceFilter(newText: String?) {
        val leadsSourceFilterList: ArrayList<LeadDataBase> = ArrayList()
        // Log.d("cod",couponArrayList.toString())

        for (item in leadsArrayList) {
            if (item.leadSource.toLowerCase(Locale.ROOT).trim()
                    .contains(newText?.toLowerCase(Locale.ROOT)!!.trim())
            ) {
                leadsSourceFilterList.add(item)
                //  Log.d("co",item.toString())
                mAdapter.filterList(leadsSourceFilterList)

            }
//            madapter.filterList(couponFilterList)
        }
//        }
        mAdapter.filterList(leadsSourceFilterList)
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