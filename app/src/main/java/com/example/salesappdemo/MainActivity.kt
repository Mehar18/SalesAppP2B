package com.example.salesappdemo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var headerLayout:Layout
    lateinit var profileImg:ImageView
    lateinit var navigationView: NavigationView
    lateinit var prefManager: PrefManager
    lateinit var toolbar: Toolbar
    lateinit var checkInbtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fragment = DashBoardFragment()
        loadFragment(fragment)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Dash Board")
        setSupportActionBar(toolbar)

        checkInbtn = findViewById(R.id.checkInBtn)

        checkInbtn.setText("Check out")
        checkInbtn.setTag("Check out")

        checkInbtn.setOnClickListener {

            if (checkInbtn.getTag() == "Check out"){
                checkInbtn.setText("Check in")
                checkInbtn.setTag("Check in")
            }else{
                checkInbtn.setText("Check out")
                checkInbtn.setTag("Check out")

            }


        }


        prefManager = PrefManager(this)
        drawerLayout = findViewById(R.id.drawerlayout)
        navigationView = findViewById(R.id.navigationView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

    val header = navigationView.getHeaderView(0)


//           val username:TextView = findViewById(R.id.userName)
           profileImg = header.findViewById(R.id.profileImage)
           profileImg.setOnClickListener {

               val builder = AlertDialog.Builder(this)
                   .create()
               val view = layoutInflater.inflate(R.layout.profile_dialogue,null)
               val firstName:TextView = view.findViewById(R.id.userName)
               builder.setView(view)
               builder.setCanceledOnTouchOutside(true)
               builder.show()
               drawerLayout.closeDrawer(GravityCompat.START)
               val profileName : TextView = view.findViewById(R.id.myProfileTxt)
               profileName.setOnClickListener{
                   builder.dismiss()
                   val fragment = ProfileFragment()
                   loadFragment(fragment)

               }


           }


        }





    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.DashBoard_menu -> {
                val fragment = DashBoardFragment()
                loadFragment(fragment)
                toolbar.setTitle("Dash board")


//                drawerLayout.close()
            }
            R.id.logOut_menu->{
                drawerLayout.close()
                prefManager.setLogin(false)
                val intent = Intent(this@MainActivity,LoginActivity::class.java)
                overridePendingTransition(0,0)
                intent.putExtra("suc","Success!!")
                intent.putExtra("log","Logged out successfully")
                startActivity(intent)
                finish()
            }

            R.id.coupon_menu->{
                val fragment = CouponFragment()
                loadFragment(fragment)
                toolbar.setTitle("Coupon")

            }
            R.id.reports_menu->{
                val fragment = ReportFragment()
                loadFragment(fragment)
                toolbar.setTitle("Report")
            }

            R.id.leads_menu->{
                val fragment = LeadsFragment()
                loadFragment(fragment)
                toolbar.setTitle("Leads")
            }
            R.id.leadStatus_menu->{
                val fragment = LeadStatus()
                loadFragment(fragment)
                toolbar.setTitle("Leads status")
            }
        }

       drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}