package com.example.salesappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.salesappdemo.LeadsFragment
import com.example.salesappdemo.R
import com.example.salesappdemo.data.LeadDataBase
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LeadsAddRecordFragment : Fragment() {

    val selectSourceArrayList: ArrayList<String> = ArrayList()
    val selectStatusArrayList: ArrayList<String> = ArrayList()
    lateinit var selectedLeadSourceString:String
    lateinit var selectedLeadStatusString:String
    lateinit var dbReference:DatabaseReference



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_leads_add_record, container, false)

        val submitButton: Button = view.findViewById(R.id.leadsSubmitButton)

        val name :TextInputEditText= view.findViewById(R.id.leadsAddEmployeeName)
        val email: TextInputEditText = view.findViewById(R.id.leadsAddEmployeeEmail)
        val mobileNumber: TextInputEditText = view.findViewById(R.id.leadsAddEmployeeMobileNumber)
        val editRemark:TextInputEditText = view.findViewById(R.id.leadsRemark)
        dbReference = FirebaseDatabase.getInstance().getReference("Add Leads")

        selectSourceArrayList.add("--Select Source--")
        selectSourceArrayList.add("Website")
        selectSourceArrayList.add("Webinar")
        selectSourceArrayList.add("Social Media")

        val selectSourceAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,selectSourceArrayList
        )
        val selectSourceSpinner: Spinner = view.findViewById(R.id.leadsSourceSpinner)
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

            }
        }

        selectStatusArrayList.add("--Select Status--")
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
        val selectStatusSpinner: Spinner = view.findViewById(R.id.leadsSelectStatusSpinner)
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




            }
        }




        submitButton.setOnClickListener {
            val fragment = LeadsFragment()

            val leadName  = name.text.toString()
            val leadEmail = email.text.toString()
            val mobile = mobileNumber.text.toString()
            val leadSource = selectedLeadSourceString
            val leadStatus = selectedLeadStatusString
            val createdAt = ""
            val updatedAt = ""
            val leadOwner = ""
            loadFragment(fragment)

            val leadsData = LeadDataBase(leadName,leadEmail,mobile,leadSource,leadStatus
            ,createdAt,updatedAt,leadOwner)

            dbReference.child(leadName).setValue(leadsData)



        }
        return view
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
