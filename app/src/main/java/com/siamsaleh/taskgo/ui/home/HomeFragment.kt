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
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

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

    private fun handleRecommendedList(recommendedListUiState: UiState<List<RecommendedItem>>) {

        when (recommendedListUiState) {
            is UiState.Loading -> {
                // Nothing to do..
            }

            is UiState.Error -> {
                showToast(recommendedListUiState.message)
            }

            is UiState.Success -> {
                handleRecommendedListSuccess(recommendedListUiState.data)
            }
        }
    }

    private fun handleRecommendedListSuccess(data: List<RecommendedItem>) {
        //Log.d("UISTATE", "handleRecommendedListSuccess: $data")
    }

    private fun loadLiveData() {
        lifecycleScope.launch {
            viewModel.getRecommendedPlace()
        }
    }
}