package com.example.salesappdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.salesappdemo.databinding.FragmentAddRecordCouponBinding


class AddRecordCouponFragment : Fragment() {
    private lateinit var binding: FragmentAddRecordCouponBinding
    private lateinit var prefManager: PrefManager


    val typeArrayList: ArrayList<String> = ArrayList()
   // lateinit var listViewType: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        prefManager = PrefManager(requireContext())
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_add_record_coupon, container, false)
        binding = FragmentAddRecordCouponBinding.inflate(inflater,container,false)



        val typeSpinner : Spinner = view.findViewById(R.id.typeSpinner)
      //  val typeList : ListView = view.findViewById(R.id.listType)

//        typeSpinner.prompt = "Select"
        typeArrayList.add("Select")
        typeArrayList.add("Percent")
        typeArrayList.add("Fixed")

        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,typeArrayList
        )
        typeSpinner.setAdapter(spinnerAdapter)

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.toString()

            }

        }


        return view
    }
}