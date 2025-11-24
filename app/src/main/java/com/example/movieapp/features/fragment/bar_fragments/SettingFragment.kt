package com.example.movieapp.features.fragment.bar_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var binding_: FragmentSettingBinding? = null
    private val binding get() = binding_!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

}