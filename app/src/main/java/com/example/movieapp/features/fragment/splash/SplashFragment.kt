package com.example.movieapp.features.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.SplashFragmentBinding

class SplashFragment : Fragment() {
    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!
private val viewModel : SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()



         viewModel.nav.observe(viewLifecycleOwner){
             if (it == true )
             {
              findNavController().navigate(R.id.charactersFragment)
                 viewModel.stop()
             }
         }

    }


}