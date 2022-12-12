package com.fadli.catcare.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fadli.catcare.ui.auth.AuthActivity
import com.fadli.catcare.ui.dashboard.profile.ProfileFragment
import com.fadli.catcare.R
import com.fadli.catcare.databinding.ActivityMainBinding
import com.fadli.catcare.ui.dashboard.calendar.CalendarFragment
import com.fadli.catcare.ui.dashboard.care.CareActivity
import com.fadli.catcare.ui.dashboard.gallery.GalleryActivity
import com.fadli.catcare.ui.dashboard.home.HomeFragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentProfile = ProfileFragment()
    private val fragmentHome = HomeFragment()
    private val fragmentCalendar = CalendarFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.background = null // hide abnormal layer in bottom nav

        switchFragment(fragmentHome)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> switchFragment(fragmentHome)
                R.id.navigation_profile -> switchFragment(fragmentProfile)
                R.id.navigation_care -> startActivity(Intent(this, CareActivity::class.java))
                R.id.navigation_gallery -> startActivity(Intent(this, GalleryActivity::class.java))
                R.id.navigation_calendar -> switchFragment(fragmentCalendar)
            }
            true
        }

    }



    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }



    fun routeToAuth() = startActivity(Intent(this, AuthActivity::class.java))

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}