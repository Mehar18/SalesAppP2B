package com.example.salesappdemo

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.salesappdemo.data.CouponDataBase
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar


class AddRecordCouponFragment : Fragment() {
    private lateinit var prefManager: PrefManager
    lateinit var selectedItemStringType : String
    lateinit var selectedItemStringEmployee : String
    lateinit var selectedItemStringCourse : String
    lateinit var couponAdapter: CouponAdapter
    val arrayCoupon: ArrayList<CouponDataBase> = ArrayList()
    lateinit var typeSpinner:Spinner


    val typeArrayList: ArrayList<String> = ArrayList()
    val employeeArrayList: ArrayList<String> = ArrayList()
    val coursesArrayList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        prefManager = PrefManager(requireContext())
        super.onCreate(savedInstanceState)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_record_coupon, container, false)
        //binding = FragmentAddRecordCouponBinding.inflate(inflater,container,false)
        val submitButton:Button = view.findViewById(R.id.submitButton)
        val startDateEdt:TextInputEditText = view.findViewById(R.id.startDate)
        val expiryDateEdt:TextInputEditText = view.findViewById(R.id.ExpiryDate)



        //array for type spinner
        typeArrayList.add("--Select--")
        typeArrayList.add("Percent")
        typeArrayList.add("Fixed")


        //array for employee spinner
        employeeArrayList.add("--Select--")

        //array for courses
        coursesArrayList.add("--Select--")
        coursesArrayList.add("Complete full stack development")

        //submit button click
            submitButton.setOnClickListener {
//            val fragment = CouponFragment()
//                loadFragment(fragment)
                //type spinner
                val couponEdt: TextInputEditText = view.findViewById(R.id.couponCode)
                val amountEdt: TextInputEditText = view.findViewById(R.id.AmountEdt)
                val startDateEdt: TextInputEditText=view.findViewById(R.id.startDate)
                val expiryByEdt: TextInputEditText=view.findViewById(R.id.ExpiryDate)
                //employee
                //course


              //  val typeString = selectedItemStringType
               // val employeeString = selectedItemStringEmployee
                val couponString = couponEdt.text.toString()
                val amountString = amountEdt.text.toString().toInt()
                val startDateString=startDateEdt.text.toString()
                val expiryDateString = expiryByEdt.text.toString()
                //val courseString = selectedItemStringEmployee
                val bundle = Bundle()






                var couponDatabase = FirebaseDatabase.getInstance().getReference("Add coupon")
                couponDatabase.child(couponString).setValue(
                    CouponDataBase(selectedItemStringType,couponString,amountString
                ,startDateString,expiryDateString,selectedItemStringEmployee,selectedItemStringCourse)
                )
//






        }

        //type spinner

        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,typeArrayList
        )
        typeSpinner = view.findViewById(R.id.typeSpinner)
        typeSpinner.setAdapter(spinnerAdapter)

            typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItemStringType = selectedItem
//                Log.d("selectedItem",selectedItemString)


            }

        }

        //employee spinner
        val employeeSpinnerAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(),android.R.layout.simple_spinner_dropdown_item,employeeArrayList
        )
        val employeeSpinner : Spinner = view.findViewById(R.id.employeeSpinner)
        employeeSpinner.setAdapter(employeeSpinnerAdapter)
        employeeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItemStringEmployee = selectedItem
            }
        }


        //courseSpinner
        val courseSpinnerAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            coursesArrayList
        )
        val courseSpinner: Spinner = view.findViewById(R.id.courseSpinner)
        courseSpinner.setAdapter(courseSpinnerAdapter)
        courseSpinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItemStringCourse=selectedItem
            }
        }

        //start date
        startDateEdt.setOnClickListener {
            val StartDateCalender = Calendar.getInstance()
            val StartYear = StartDateCalender.get(Calendar.YEAR)
            val StartMonth = StartDateCalender.get(Calendar.MONTH)
            val StartDay = StartDateCalender.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    val Startdate = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    startDateEdt.setText(Startdate)

                },
                StartYear,
                StartMonth,
                StartDay
            )

            datePickerDialog.show()
        }

        //expiryDate

        expiryDateEdt.setOnClickListener {
            val ExpiryDateCalender = Calendar.getInstance()
            val ExpYear = ExpiryDateCalender.get(Calendar.YEAR)
            val ExpMonth = ExpiryDateCalender.get(Calendar.MONTH)
            val ExpDay = ExpiryDateCalender.get(Calendar.DAY_OF_MONTH)
            val datePicker = DatePickerDialog(
                requireContext(),
                {view, year, monthOfYear, dayOfMonth ->
                    val expDate = (dayOfMonth.toString()+ "-"+(monthOfYear + 1) + "-" + year)
                    expiryDateEdt.setText(expDate)
                },
                ExpYear,
                ExpMonth,
                ExpDay
            )
            datePicker.show()
        }


        return view
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}