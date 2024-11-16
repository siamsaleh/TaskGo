package com.siamsaleh.taskgo.ui.screens.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.ActivityDetailsBinding
import com.siamsaleh.taskgo.ui.base.BaseActivity
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserver()

        loadLiveData()

        binding.ivBackArrow.setOnClickListener {
            finish()
        }

        // List of image URLs
        val imageUrls = listOf(
            "https://picsum.photos/300/200",
            "https://picsum.photos/id/237/300/200",
            "https://picsum.photos/seed/picsum/300/200"
        )

        // Set the images
        binding.customImageSlider.setImages(imageUrls)
    }

    private fun setupObserver() {
        viewModel.recommendedItem.observe(this) {
            handleRecommendedItem(it)
        }
    }

    private fun handleRecommendedItem(recommendedItemState: UiState<RecommendedItem>){

        when (recommendedItemState) {
            is UiState.Loading -> {
                // Nothing to do..
            }

            is UiState.Error -> {
                showToast(recommendedItemState.message)
            }

            is UiState.Success -> {
                handleRecommendedItemSuccess(recommendedItemState.data)
            }
        }
    }

    private fun handleRecommendedItemSuccess(recommendedItem: RecommendedItem) {
        //binding.image.loadImage(this@DetailsActivity, recommendedItem.detailImages?.get(0))
    }

    private fun loadLiveData() {
        val recommendedItem =
            intent.serializable<RecommendedItem>(RecommendedItem.RECOMMENDED_ITEM_KEY)

        lifecycleScope.launch {
            viewModel.setRecommendedItem(recommendedItem)
        }
    }
}