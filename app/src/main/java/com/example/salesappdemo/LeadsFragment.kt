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
import com.example.salesappdemo.data.ModelLeadsDataClass


class LeadsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leads, container, false)
        val recyclerViewLeads = view.findViewById<RecyclerView>(R.id.recyclerViewLeads)
        recyclerViewLeads.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewLeads.adapter = LeadsAdapter(LeadsList)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE), Companion.PERMISSION_CODE
            )

        }

        val NewRecordButton: Button = view.findViewById(R.id.newRecordBtn)
        NewRecordButton.setOnClickListener {

            val fragment = LeadsNewRecordFRagment()
            loadFragment(fragment)

        }

        return view
    }

    private val LeadsList = ArrayList<ModelLeadsDataClass>().apply {
        add(ModelLeadsDataClass("name","mmmmm@gmail.com","9043410234",
            "website","n/a","date","date","name"))

    }
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