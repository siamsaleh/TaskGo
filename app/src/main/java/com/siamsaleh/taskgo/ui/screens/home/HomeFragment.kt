package com.siamsaleh.taskgo.ui.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.siamsaleh.taskgo.R
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.FragmentHomeBinding
import com.siamsaleh.taskgo.ui.base.BaseFragment
import com.siamsaleh.taskgo.ui.screens.details.DetailsActivity
import com.siamsaleh.taskgo.ui.adapters.RecommendedAdapter
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
                binding.rvRecommended.visibility = View.GONE
                binding.tvStatus.visibility = View.GONE
                binding.loadingEffect.visibility = View.VISIBLE
            }

            is UiState.Error -> {
                binding.tvStatus.text = recommendedResult.message

                binding.rvRecommended.visibility = View.GONE
                binding.loadingEffect.visibility = View.GONE
                binding.tvStatus.visibility = View.VISIBLE
            }

            is UiState.Success -> {
                binding.loadingEffect.visibility = View.GONE

                if (recommendedResult.data.isNotEmpty()) {
                    binding.rvRecommended.adapter =
                        RecommendedAdapter(
                            requireContext(),
                            recommendedResult.data,
                            this@HomeFragment
                        )

                    binding.tvStatus.visibility = View.GONE
                    binding.rvRecommended.visibility = View.VISIBLE

                } else {
                    binding.tvStatus.text = getString(R.string.no_data_found_text)

                    binding.rvRecommended.visibility = View.GONE
                    binding.tvStatus.visibility = View.VISIBLE
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
        startActivity(Intent(requireActivity(), DetailsActivity::class.java).apply {
            putExtra(RecommendedItem.RECOMMENDED_ITEM_KEY, recommendedItem)
        })
    }
}