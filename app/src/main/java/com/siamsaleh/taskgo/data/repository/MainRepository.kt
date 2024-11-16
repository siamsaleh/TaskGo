package com.siamsaleh.taskgo.data.repository

import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.data.remote.ApiService
import com.siamsaleh.taskgo.domain.usecase.MainUseCase
import com.siamsaleh.taskgo.util.UiState
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : MainUseCase {

    override suspend fun getRecommendedPlace(): UiState<List<RecommendedItem>> {
        return try {
            UiState.Success(apiService.getRecommendedPlace())
        } catch (ex: Exception){
            UiState.Error(ex.message ?: "Unknown error occurred")
        }
    }

    override suspend fun setRecommendedItem(recommendedItem: RecommendedItem?): UiState<RecommendedItem> {
        return if (recommendedItem != null) {
            UiState.Success(recommendedItem)
        } else {
            UiState.Error("Data not found")
        }
    }
}