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
import com.siamsaleh.taskgo.ui.adapters.RecommendedHorizontalAdapter
import com.siamsaleh.taskgo.ui.listener.OnItemClickListener
import com.siamsaleh.taskgo.ui.screens.seedetails.SeeDetailsActivity
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(), OnItemClickListener {

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

                binding.apply {
                    rvRecommended.visibility = View.GONE
                    tvStatus.visibility = View.GONE
                    loadingEffect.visibility = View.VISIBLE
                }
            }

            is UiState.Error -> {

                binding.apply {
                    tvStatus.text = recommendedResult.message

                    rvRecommended.visibility = View.GONE
                    loadingEffect.visibility = View.GONE
                    tvStatus.visibility = View.VISIBLE
                }
            }

            is UiState.Success -> {
                binding.loadingEffect.visibility = View.GONE

                if (recommendedResult.data.isNotEmpty()) {

                    binding.apply {
                        rvRecommended.adapter = RecommendedHorizontalAdapter(
                            context = requireContext(),
                            recommendedList = recommendedResult.data,
                            onItemClickListener = this@HomeFragment
                        )

                        // Sent recommendedList for reducing Api call
                        seeAllBtn.setOnClickListener {
                            startActivity(Intent(requireActivity(), SeeDetailsActivity::class.java).apply {
                                putExtra(RecommendedItem.RECOMMENDED_LIST_KEY, ArrayList(recommendedResult.data))
                            })
                        }

                        tvStatus.visibility = View.GONE
                        rvRecommended.visibility = View.VISIBLE
                    }

                } else {

                    binding.apply {
                        tvStatus.text = getString(R.string.no_data_found_text)

                        rvRecommended.visibility = View.GONE
                        tvStatus.visibility = View.VISIBLE
                    }
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