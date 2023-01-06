package com.example.salesappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesappdemo.adapter.LeadStatusAdapter
import com.example.salesappdemo.data.LeadStatusData


class LeadStatus : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_lead_status, container, false)




        val recyclerViewLeadStatus = view.findViewById<RecyclerView>(R.id.leadStatusRecyclerView)
        recyclerViewLeadStatus.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        val adapter = LeadStatusAdapter(leadStatusList)
        recyclerViewLeadStatus.adapter = adapter
        return view


    }

    private val leadStatusList = ArrayList<LeadStatusData>().apply {
        add(
            LeadStatusData("Aadil","aadilkhatri97861@gmail.com","9408734222",
            "Webinar","Switch off","1970-01-01 00:00:00",
                "N/A","Anmol","N/A","2022-10-29"
            )
        )
        add(LeadStatusData("A.Shophia","ashophia3@gmail.com","7200334984",
            "Webinar","Ringing","1970-01-01 05:30:00",
        "N/A","Anmol","N/A","2022-10-29"))
        add(
            LeadStatusData("Aadil","aadilkhatri97861@gmail.com","9408734222",
                "Webinar","Switch off","1970-01-01 00:00:00",
                "N/A","Anmol","N/A","2022-10-29"
            )
        )
        add(LeadStatusData("A.Shophia","ashophia3@gmail.com","7200334984",
            "Webinar","Ringing","1970-01-01 05:30:00",
            "N/A","Anmol","N/A","2022-10-29"))
        add(
            LeadStatusData("Aadil","aadilkhatri97861@gmail.com","9408734222",
                "Webinar","Switch off","1970-01-01 00:00:00",
                "N/A","Anmol","N/A","2022-10-29"
            )
        )
        add(LeadStatusData("A.Shophia","ashophia3@gmail.com","7200334984",
            "Webinar","Ringing","1970-01-01 05:30:00",
            "N/A","Anmol","N/A","2022-10-29"))

    }





}