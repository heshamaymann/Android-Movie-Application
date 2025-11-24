package com.example.movieapp.features.fragment.characters



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment() {
    private lateinit var adapter: CharactersAdapter
    private var binding_: FragmentCharactersBinding? = null
    private val binding get() = binding_!!
    private val viewModel: CharacterViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observers()
    }

    private fun observers() {
        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Progress -> {
                    //show hide progress
                    binding.load.isVisible = it.isLoading
                }

                is Resource.Success -> {
                    adapter.setList(it.data.list)
//                    adapter.submitList(it.data.list.toMutableList())
//                    adapter.notifyItemRangeInserted(0, it.data.list.size)
                }
            }
        }

    }

    private fun initUi() {
        adapter = CharactersAdapter {
            findNavController().navigate(R.id.detailsFragment, bundleOf("characterID" to it.id))
        }
        binding.charList.adapter = adapter
    }
//returned from api
//        adapter.submitList()

}

