package com.example.movieapp.features.fragment.bar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentSportBinding

class SportFragment : Fragment() {
    private var binding_: FragmentSportBinding? = null
    private val binding get() = binding_!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentSportBinding.inflate(inflater, container, false)
        return binding.root
    }

}