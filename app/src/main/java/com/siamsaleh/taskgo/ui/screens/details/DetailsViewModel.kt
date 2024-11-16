package com.siamsaleh.taskgo.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siamsaleh.taskgo.data.model.RecommendedItem
import com.siamsaleh.taskgo.domain.usecase.MainUseCase
import com.siamsaleh.taskgo.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel(){

    private val _recommendedItem = MutableLiveData<UiState<RecommendedItem>>()
    val recommendedItem: LiveData<UiState<RecommendedItem>> get() = _recommendedItem

    suspend fun setRecommendedItem(recommendedItem: RecommendedItem?) {
        _recommendedItem.postValue(UiState.Loading)
        viewModelScope.launch {
            _recommendedItem.postValue(mainUseCase.setRecommendedItem(recommendedItem))
        }
    }
}