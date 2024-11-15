package com.siamsaleh.taskgo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.siamsaleh.taskgo.R
import com.siamsaleh.taskgo.databinding.ActivityHomeBinding
import com.siamsaleh.taskgo.ui.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load the default fragment
        loadDefaultFragment(savedInstanceState)
    }

    private fun loadDefaultFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}