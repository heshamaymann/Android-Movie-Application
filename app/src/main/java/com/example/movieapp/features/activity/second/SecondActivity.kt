package com.example.movieapp.features.activity.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivitySecondBinding
import com.example.movieapp.features.activity.second.navdrawer.MenuItem
import com.example.movieapp.features.activity.second.navdrawer.NavAdapter

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var adapter: NavAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Add layout
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(
            MenuItem("Inbox", R.drawable.inbox, 1),
            MenuItem("Outbox", R.drawable.outbox, 2),
            MenuItem("Favourite", R.drawable.favourite, 3),
            MenuItem("Label1", R.drawable.label, 4),
            MenuItem("Label2", R.drawable.label, 5)

        )
        adapter = NavAdapter {

            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        adapter.submitList(list.toMutableList())

        binding.menuitems.adapter = adapter

    }




    private fun showDrawer(show:Boolean){

        if(show){
            binding.drawerLayout.openDrawer(GravityCompat.START)

        }else{
            binding.drawerLayout.closeDrawer(GravityCompat.START)

        }
    }
}