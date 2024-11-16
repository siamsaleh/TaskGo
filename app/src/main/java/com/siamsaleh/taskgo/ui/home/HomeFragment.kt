package com.siamsaleh.taskgo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.FragmentHomeBinding
import com.siamsaleh.taskgo.ui.BaseFragment
import com.siamsaleh.taskgo.ui.home.adapter.RecommendedAdapter
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(), RecommendedAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup observer
        setupObserver()

        // Load live data
        loadLiveData()
    }

    private fun setupObserver() {
        viewModel.recommendedList.observe(viewLifecycleOwner) {
            handleRecommendedList(it)
        }
    }

    private fun handleRecommendedList(recommendedResult: UiState<List<RecommendedItem>>) {

        when (recommendedResult) {
            is UiState.Loading -> {
                // Nothing to do..
            }

            is UiState.Error -> {
                showToast(recommendedResult.message)
            }

            is UiState.Success -> {
                if (recommendedResult.data.isNotEmpty()) {
                    binding.rvRecommended.adapter =
                        RecommendedAdapter(
                            requireContext(),
                            recommendedResult.data,
                            this@HomeFragment
                        )
                }
            }
        }
    }

    private fun loadLiveData() {
        lifecycleScope.launch {
            viewModel.getRecommendedPlace()
        }
    }

    override fun onItemClick(recommendedItem: RecommendedItem) {
        recommendedItem.location?.let { showToast(it) }
    }
}