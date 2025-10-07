package com.dapascript.movieleak.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dapascript.movieleak.databinding.FragmentDetailBinding
import com.dapascript.movieleak.presentation.viewmodel.DetailViewModel
import com.dapascript.movieleak.R

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // TODO: Observe data from ViewModel and update the UI
        // viewModel.movie.observe(viewLifecycleOwner) { movie ->
        //     binding.tvMovieTitle.text = movie.title
        //     binding.tvReleaseDate.text = movie.releaseDate
        //     binding.tvOverview.text = movie.overview
        //     // Load image with Glide or Picasso
        //     // Glide.with(this).load(movie.posterUrl).into(binding.ivMoviePoster)
        // }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}