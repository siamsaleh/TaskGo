package com.siamsaleh.taskgo.domain.usecase

import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.util.UiState

interface MainUseCase {
    suspend fun getRecommendedPlace(): UiState<List<RecommendedItem>>
    suspend fun setRecommendedItem(recommendedItem: RecommendedItem?): UiState<RecommendedItem>
}