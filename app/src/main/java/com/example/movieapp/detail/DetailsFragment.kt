package com.example.movieapp.detail

import android.content.Intent
import android.net.Uri
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
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.core.Util.loadImage
import com.example.movieapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var adapter1: EpisodesAdapter
    private var binding_: FragmentDetailsBinding? = null

    private val binding get() = binding_!!
    private val viewModel1 :  DetailsViewModel by viewModels()
    private val args by navArgs<DetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding_ = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter1 = EpisodesAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            if (intent.resolveActivity(requireActivity().packageManager) != null) {

                startActivity(intent)
            } else {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loc.setOnClickListener {
            findNavController().navigate(R.id.locationFragment , bundleOf("charID" to args.characterID))
        }
        viewModel1.getCharDetail(args.characterID)
        viewModel1.data.observe(viewLifecycleOwner){
            when(it){
                is Resource.Failure ->
                {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Progress -> {
                    binding.loading.isVisible = it.isLoading
                }
                is Resource.Success -> {
                    binding.id.text =  it.data.id.toString()
                    binding.imageView.loadImage(it.data.image)
                    binding.status.text = it.data.status
                    binding.gender.text = it.data.gender
                    binding.name.text = it.data.name
                    adapter1.submitList(it.data.episode.toMutableList())
                    adapter1.notifyItemRangeInserted(0, it.data.episode.size)

                    ///adapter setlist here
                }


            }
        }
        binding.episodesList.adapter = adapter1
    }

}