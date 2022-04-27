package com.example.sampleui.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sampleui.R
import com.example.sampleui.databinding.ActivityMainBinding
import com.example.sampleui.utils.Helper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavController()
    }

    private fun setNavController() {
        try {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_container_view) as NavHostFragment
            navController = navHostFragment.navController
            binding.navigationBottom.setupWithNavController(navController)

        } catch (e: Exception) {
            e.message?.let { Helper.print(it) }
        }

    }
}