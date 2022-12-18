package com.example.salesappdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class ReportFragment : Fragment() {
    val arrayGroup : ArrayList<String> = ArrayList()
    val arrayUser : ArrayList<String> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        //Group spinner
        arrayGroup.add("--Group--")
        val GroupAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,arrayGroup
        )

        val GroupSpinner: Spinner = view.findViewById(R.id.groupSpinner)
        GroupSpinner.setAdapter(GroupAdapter)

        GroupSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()


            }

        }

        //User spinner
        arrayUser.add("--User--")
        val UserAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,arrayUser
        )

        val UserSpinner: Spinner = view.findViewById(R.id.userSpinner)
        UserSpinner.setAdapter(UserAdapter)

        UserSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()


            }

        }


        return view

    }
}

