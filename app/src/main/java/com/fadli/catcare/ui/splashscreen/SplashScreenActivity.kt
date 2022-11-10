package com.fadli.catcare.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.fadli.catcare.data.viewmodel.SettingViewModel
import com.fadli.catcare.data.viewmodel.ViewModelSettingFactory
import com.fadli.catcare.ui.auth.AuthActivity
import com.fadli.catcare.databinding.ActivitySplashScreenBinding
import com.fadli.catcare.ui.dashboard.MainActivity
import com.fadli.catcare.utils.Constanta
import com.fadli.catcare.utils.SettingPreferences
import com.fadli.catcare.utils.dataStore
import java.util.*
import kotlin.concurrent.schedule


@SuppressLint("CustomSplashScreen")
class  SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel =
            ViewModelProvider(this, ViewModelSettingFactory(pref))[SettingViewModel::class.java]

        settingViewModel.getUserPreferences(Constanta.UserPreferences.UserToken.name)
            .observe(this) { token ->
                if (token == Constanta.preferenceDefaultValue) Timer().schedule(2000) {
                    startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
                    finish()
                } else Timer().schedule(2000) {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }
            }
    }
}