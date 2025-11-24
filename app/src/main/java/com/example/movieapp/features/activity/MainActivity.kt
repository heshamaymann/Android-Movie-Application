package com.example.movieapp.features.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var con : NavController? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        con = navHostFragment.navController

    binding.bottomNavigation.setOnItemSelectedListener{
            item ->
        when(item.itemId) {
            R.id.item_1 -> {
             con!!.navigate(R.id.sportFragment)
                true
            }
            R.id.item_2 -> {
                con!!.navigate(R.id.settingFragment)
                true
            }
            R.id.item_3 -> {
                con!!.navigate(R.id.newFragment)
                true
            }
            else -> false

        }
    }
    }


}
