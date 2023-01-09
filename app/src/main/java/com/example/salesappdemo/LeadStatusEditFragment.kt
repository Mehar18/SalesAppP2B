package com.example.salesappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class LeadStatusEditFragment : Fragment() {

    val selectSourceArrayList: ArrayList<String> = ArrayList()
    val selectStatusArrayList: ArrayList<String> = ArrayList()
    lateinit var selectedLeadSourceString: String
    lateinit var selectedLeadStatusString: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.leads_status_edit_fragment, container, false)
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
        val selectStatusAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, selectStatusArrayList
        )
        val selectStatusSpinner: Spinner = mView.findViewById(R.id.leadStatusSpinner)
        selectStatusSpinner.setAdapter(selectStatusAdapter)
        selectStatusSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
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

        selectSourceArrayList.add("--Select Source--")
        selectSourceArrayList.add("Website")
        selectSourceArrayList.add("Webinar")
        selectSourceArrayList.add("Social Media")
        val selectSourceAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, selectSourceArrayList
        )
        val selectSourceSpinner: Spinner = mView.findViewById(R.id.leadStatusSourceSpinner)
        selectSourceSpinner.setAdapter(selectSourceAdapter)
        selectSourceSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
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





            return mView
        }


        companion object {

        }
    }