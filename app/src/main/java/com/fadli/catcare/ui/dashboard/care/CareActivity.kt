package com.fadli.catcare.ui.dashboard.care

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.fadli.catcare.R
import com.fadli.catcare.databinding.ActivityCareBinding
import com.fadli.catcare.ui.auth.AuthActivity
import com.fadli.catcare.ui.care.*


@Suppress("DEPRECATION")
class CareActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCareBinding
    private val fragmentKuku = KukuFragment()
    private val fragmentWeight = WeightFragment()
    private val fragmentFleas = FleasFragment()
    private val fragmentLitterbox = LitterboxFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        switchFragment(fragmentKuku)
        setupAction1()
        setupAction2()
        setupAction3()
        setupAction4()
    }

    private fun setupAction1() {
        binding.ivKuku.setOnClickListener {
            switchFragment(fragmentKuku)
        }
    }

    private fun setupAction2() {
        binding.ivBerat.setOnClickListener {
            switchFragment(fragmentWeight)

        }
    }

    private fun setupAction3() {
        binding.ivKutu.setOnClickListener {
            switchFragment(fragmentFleas)

        }
    }

    private fun setupAction4() {
        binding.ivLitterbox.setOnClickListener {
            switchFragment(fragmentLitterbox)

        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }



    fun routeToAuth() = startActivity(Intent(this, AuthActivity::class.java))

    /*override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }*/
}