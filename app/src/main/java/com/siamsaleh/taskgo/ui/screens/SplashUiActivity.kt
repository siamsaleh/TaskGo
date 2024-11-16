package com.siamsaleh.taskgo.ui.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.siamsaleh.taskgo.R
import com.siamsaleh.taskgo.ui.screens.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashUiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_ui)

        // Delay for a few seconds, then start the next activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start your main activity after the splash
            startActivity(Intent(this, HomeActivity::class.java))
            finish()  // Finish the splash screen so the user can't go back to it
        }, 3000) // Show splash for 3 seconds
    }
}