package com.example.salesappdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.salesappdemo.R.id.navigationView
import com.example.salesappdemo.R.id.toolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var Navigationview: NavigationView
    lateinit var prefManager: PrefManager
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragment = DashBoardFragment()
        loadFragment(fragment)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Dash Board")
        setSupportActionBar(toolbar)

        prefManager = PrefManager(this)



        drawerLayout = findViewById(R.id.drawerlayout)
        Navigationview = findViewById(navigationView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        Navigationview.setNavigationItemSelectedListener(this)

        Navigationview.getHeaderView(0).setOnClickListener{

        }



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.DashBoard_menu -> {
                val fragment = DashBoardFragment()
                loadFragment(fragment)

//                drawerLayout.close()
            }
            R.id.logOut_menu->{
                drawerLayout.close()
                Log.d("hh","clicked")
                prefManager.setLogin(false)
                val intent = Intent(this@MainActivity,LoginActivity::class.java)
                overridePendingTransition(0,0)
                startActivity(intent)
                finish()
            }

            R.id.coupon_menu->{
                val fragment = couponFragment()
                loadFragment(fragment)
                toolbar.setTitle("coupon")

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