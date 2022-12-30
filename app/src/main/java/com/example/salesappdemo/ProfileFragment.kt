package com.example.salesappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class ProfileFragment : Fragment() {
    lateinit var firstNameEdt:EditText
    lateinit var LastNameEdt:EditText
    lateinit var emailEdt:EditText
    lateinit var phoneEdt:EditText
    lateinit var updateBtn:Button




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_profile_, container, false)


        firstNameEdt=view.findViewById(R.id.firstNameProfileEdt)
        LastNameEdt=view.findViewById(R.id.lastNameProfileEdt)
        emailEdt=view.findViewById(R.id.emailProfileEdt)
        phoneEdt=view.findViewById(R.id.phoneProfileEdt)
        updateBtn=view.findViewById(R.id.updatePersonalBtn)



        updateBtn.setOnClickListener {

            val firstNameString = firstNameEdt.text
            val lastNameString = LastNameEdt.text
            val emailString = emailEdt.text
            val phoneString = phoneEdt.text
            val fragment = DashBoardFragment()
            loadFragment(fragment)




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