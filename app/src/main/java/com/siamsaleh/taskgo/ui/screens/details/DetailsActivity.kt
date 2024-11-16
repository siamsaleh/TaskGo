package com.siamsaleh.taskgo.ui.screens.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.siamsaleh.taskgo.R
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.databinding.ActivityDetailsBinding
import com.siamsaleh.taskgo.ui.base.BaseActivity
import com.siamsaleh.taskgo.util.CommonUtils
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

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

        binding.apply {
            // Set the images
            customImageSlider.setImages(recommendedItem.detailImages)

            propertyText.text = recommendedItem.propertyName
            ratingText.text = recommendedItem.rating.toString()
            locationText.text = recommendedItem.location

            // Set Description
            recommendedItem.description?.let {
                CommonUtils.seeMoreText(descriptionText, it)
            }

            priceText.text = CommonUtils.formatNumberWithThousandsSeparator(recommendedItem.fare)

            val fareUnit = recommendedItem.fareUnit?.uppercase(Locale.getDefault()) ?: ""
            priceUnitText.text = this@DetailsActivity.getString(R.string.fare_unit_format, fareUnit)
        }
    }

    private fun loadLiveData() {
        val recommendedItem =
            intent.serializable<RecommendedItem>(RecommendedItem.RECOMMENDED_ITEM_KEY)

        lifecycleScope.launch {
            viewModel.setRecommendedItem(recommendedItem)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop auto-slide to avoid memory leaks
        binding.customImageSlider.stopAutoSlide()
    }
}