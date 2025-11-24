package com.example.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var binding_: FragmentRegisterBinding? = null
    private val binding get() = binding_!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.splashFragment)
           // val intent = Intent(requireContext(), SecondActivity::class.java)
            //startActivity(intent)
        }


        binding.login.setOnClickListener{
            Toast.makeText(context, "Login Now", Toast.LENGTH_SHORT).show()
        }
    }
}