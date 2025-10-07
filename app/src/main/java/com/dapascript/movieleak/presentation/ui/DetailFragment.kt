package com.dapascript.movieleak.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.dapascript.movieleak.R
import com.dapascript.movieleak.databinding.FragmentDetailBinding
import com.dapascript.movieleak.domain.model.MovieDetail
import com.dapascript.movieleak.presentation.utils.UiState
import com.dapascript.movieleak.presentation.viewmodel.DetailViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
        initAction()

        viewModel.getMovieDetail(args.movieId)
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val layoutParams = v.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = systemBars.top
            v.layoutParams = layoutParams
            insets
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.detailState.collect {
                when (it) {
                    is UiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is UiState.Success -> {
                        binding.progressBar.isVisible = false
                        initView(it.data)
                    }

                    is UiState.Error -> {
                        binding.progressBar.isVisible = false
                        binding.root.isVisible = false
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(data: MovieDetail) {
        binding.apply {
            val backdropUrl = "https://image.tmdb.org/t/p/w500${data.backdropPath}"
            ivMovieBackdrop.load(backdropUrl) {
                crossfade(true)
            }

            val posterUrl = "https://image.tmdb.org/t/p/w500${data.posterPath}"
            ivMoviePoster.load(posterUrl) {
                crossfade(true)
            }

            tvMovieTitle.text = data.title
            tvMovieScore.text = data.voteAverage.toString()
            tvReleaseDate.text = data.releaseDate
            tvRuntime.text = getString(R.string.runtime_minutes, data.runtime.toString())
            tvOverview.text = data.overview

            cgGenres.removeAllViews()
            data.genres.forEach { genre ->
                val chip = Chip(requireContext()).apply {
                    text = genre
                }
                cgGenres.addView(chip)
            }
        }
    }

    private fun initAction() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}