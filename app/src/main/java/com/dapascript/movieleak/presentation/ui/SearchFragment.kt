package com.dapascript.movieleak.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dapascript.movieleak.databinding.FragmentSearchBinding
import com.dapascript.movieleak.presentation.adapter.SearchAdapter
import com.dapascript.movieleak.presentation.utils.UiState
import com.dapascript.movieleak.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObserver()
        initAction()
    }

    private fun initAdapter() {
        searchAdapter = SearchAdapter {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.rvMovies.adapter = searchAdapter
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.searchState.collect {
                when (it) {
                    is UiState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.rvMovies.isVisible = false
                        binding.tvError.isVisible = false
                        binding.tvEmpty.isVisible = false
                    }

                    is UiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.rvMovies.isVisible = true
                        binding.tvError.isVisible = false
                        binding.tvEmpty.isVisible = it.data.isEmpty()
                        searchAdapter.submitList(it.data)
                    }

                    is UiState.Error -> {
                        binding.progressBar.isVisible = false
                        binding.rvMovies.isVisible = false
                        binding.tvError.isVisible = true
                        binding.tvEmpty.isVisible = false
                        binding.tvError.text = it.message
                    }
                }
            }
        }
    }

    private fun initAction() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchMovie(binding.etSearch.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}