package com.siamsaleh.taskgo.ui.screens.seedetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.siamsaleh.taskgo.R
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.ActivitySeeDetailsBinding
import com.siamsaleh.taskgo.ui.adapters.RecommendedVerticalAdapter
import com.siamsaleh.taskgo.ui.base.BaseActivity
import com.siamsaleh.taskgo.ui.listener.OnItemClickListener
import com.siamsaleh.taskgo.ui.screens.details.DetailsActivity
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeDetailsActivity : BaseActivity(), OnItemClickListener {

    private lateinit var binding: ActivitySeeDetailsBinding
    private val viewModel: SeeDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup observer
        setupObserver()

        // Load live data
        loadLiveData()

        binding.ivBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun loadLiveData() {
        val recommendedList: List<RecommendedItem>? =
            intent.serializable(RecommendedItem.RECOMMENDED_LIST_KEY)

        lifecycleScope.launch {
            viewModel.setRecommendedList(recommendedList)
        }
    }

    private fun setupObserver() {
        viewModel.recommendedList.observe(this@SeeDetailsActivity) {
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

                    binding.rvRecommended.layoutManager =
                        GridLayoutManager(this@SeeDetailsActivity, 2)

                    binding.rvRecommended.adapter = RecommendedVerticalAdapter(
                        context = this@SeeDetailsActivity,
                        recommendedList = recommendedResult.data,
                        onItemClickListener = this@SeeDetailsActivity
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

    override fun onItemClick(recommendedItem: RecommendedItem) {
        startActivity(Intent(this@SeeDetailsActivity, DetailsActivity::class.java).apply {
            putExtra(RecommendedItem.RECOMMENDED_ITEM_KEY, recommendedItem)
        })
    }
}