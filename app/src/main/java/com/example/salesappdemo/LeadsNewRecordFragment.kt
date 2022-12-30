package com.example.salesappdemo

import android.content.Context
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
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.example.salesappdemo.data.LeadsData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView


class LeadsNewRecordFragment : Fragment() {
    val selectSourceArrayList: ArrayList<String> = ArrayList()
    val selectStatusArrayList: ArrayList<String> = ArrayList()
    val listData: ArrayList<LeadsData> = ArrayList()
    val listData1: ArrayList<String> = ArrayList()
    lateinit var dataString:String
     var item:String? = null
    var textEdt:String?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_leads_new_record_f_ragment, container, false)
        val submitButton: Button = view.findViewById(R.id.submitButton)
//        api()

        val name :MaterialTextView = view.findViewById(R.id.addEmployeeName)
        val email:MaterialTextView = view.findViewById(R.id.addEmployeeEmail)
        val mobileNumber:MaterialTextView = view.findViewById(R.id.addEmployeeMobileNumber)

        name.text = requireArguments().get("name").toString()
        email.text = requireArguments().get("email").toString()
        mobileNumber.text = requireArguments().get("mobileNumber").toString()





        submitButton.setOnClickListener {
            val fragment  = LeadsFragment()
            loadFragment(fragment)
        }

        //select source spinner
        selectSourceArrayList.add("--Select Source--")
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
            }
            }

        //select status spinner
        selectStatusArrayList.add("--Select Status--")
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


}
