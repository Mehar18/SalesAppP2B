package com.example.salesappdemo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.salesappdemo.data.CouponDataBase
import com.example.salesappdemo.data.LeadDataBase
import com.example.salesappdemo.data.LeadsData
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class LeadsEditableFragment : Fragment() {
    val selectSourceArrayList: ArrayList<String> = ArrayList()
    val selectStatusArrayList: ArrayList<String> = ArrayList()
    lateinit var selectedLeadSourceString:String
    lateinit var selectedLeadStatusString:String
    val listData: ArrayList<LeadsData> = ArrayList()
    val listData1: ArrayList<String> = ArrayList()
    lateinit var dataString:String
     var item:String? = null
    var textEdt:String?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_leads_editable_fragment, container, false)
        val submitButton: Button = view.findViewById(R.id.submitButton)

//        api()

        val name :MaterialTextView = view.findViewById(R.id.addEmployeeName)
        val email:MaterialTextView = view.findViewById(R.id.addEmployeeEmail)
        val mobileNumber:MaterialTextView = view.findViewById(R.id.addEmployeeMobileNumber)

        name.text = requireArguments().get("name").toString()
        email.text = requireArguments().get("email").toString()
        mobileNumber.text = requireArguments().get("mobileNumber").toString()
        selectedLeadSourceString = requireArguments().get("leadSource").toString()
        selectedLeadStatusString=requireArguments().get("leadStatus").toString()





        submitButton.setOnClickListener {
            val fragment  = LeadsFragment()

            val leadName  = name.text.toString()
            val leadEmail = email.text.toString()
            val mobile = mobileNumber.text.toString()
            val leadSource = selectedLeadSourceString
            val leadStatus = selectedLeadStatusString
            val createdAtTime = arguments?.getString("createdDate")
            val createdAt = createdAtTime.toString()

            val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd  '' HH:mm:ss ")
            val currentDateAndTime: String = simpleDateFormat.format(Date())
            val updatedAt = currentDateAndTime
            val leadOwner = ""

            updateData(leadName,leadEmail,mobile,leadSource,leadStatus,createdAt,updatedAt
            ,leadOwner)
            loadFragment(fragment)

        }

        //select source spinner
       selectSourceArrayList.add(selectedLeadSourceString)
//        selectSourceArrayList.add("--Select Source--")
//        selectSourceArrayList.add("Website")
//        selectSourceArrayList.add("Webinar")
//        selectSourceArrayList.add("Social Media")
        val selectSourceAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,selectSourceArrayList
        )
        val selectSourceSpinner: Spinner = view.findViewById(R.id.sourceSpinner)
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

        //select status spinner
        selectStatusArrayList.add("--Select Status--")
        selectStatusArrayList.add("N/A")
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
      //  selectStatusArrayList.add(selectedLeadStatusString)
        val selectStatusAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,selectStatusArrayList
        )
        val selectStatusSpinner: Spinner = view.findViewById(R.id.selectStatusSpinner)
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


        return view
    }


    private fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
//    private fun api(){
//        val url = "https://perfectplanbportal.com/api/courses"
//        val getRequest: com.android.volley.toolbox.JsonObjectRequest = object : com.android.volley.toolbox.JsonObjectRequest(
//            Method.GET, url,null,
//            Response.Listener {
//                val LeadArray = it.getJSONArray("data")
//                for (i in 0 until LeadArray.length()){
//                    val courseObject = LeadArray.getJSONObject(i)
//                    var textTitle = courseObject.get("title").toString()
//                    item = LeadsData(textTitle).toString()
//                    listData1.add(textTitle.toString())
//                    textEdt = listData1.toString()
//                    Log.d("msg",textEdt.toString())
//
//                    nameEdt.setText(textEdt)
//
//                }
//                              },
//            Response.ErrorListener { error ->
//                Log.d("ERROR", "error => $error")
//            }
//        ) {
//            @Throws(AuthFailureError::class)
//            override fun getHeaders(): Map<String, String> {
//                val headers: MutableMap<String, String> = HashMap()
//                headers["X-Api-Key"] = "test_eperfect-hskgyujr6-85bv-sjrj-3jase80"
//                headers["X-Access-Token"] = "test_92571Bhf_JHdbj_ljm12_41d03d737da5129c665b"
//                return headers
//            }
//        }
//        activity?.applicationContext?.let { MySingleton.getInstance(it).addToRequestQueue(getRequest) }
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


    private fun updateData( name: String,
                            email : String,
                            mobile : String,
                            leadSource : String,
                            status : String,
                            createdAt : String,
                            updatedAt : String,
                            leadOwner : String,){

        val dbReferenceLeads = FirebaseDatabase.getInstance().getReference("Add Leads").child(name)
        val leadsUpdateData = LeadDataBase(name,
        email,mobile,leadSource,status,createdAt,updatedAt,leadOwner)
        dbReferenceLeads.setValue(leadsUpdateData)





    }



}
