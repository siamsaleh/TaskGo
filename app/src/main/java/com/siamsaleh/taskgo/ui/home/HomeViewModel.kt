package com.siamsaleh.taskgo.ui.home

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
class HomeViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _recommendedList = MutableLiveData<UiState<List<RecommendedItem>>>()
    val recommendedList: LiveData<UiState<List<RecommendedItem>>> get() = _recommendedList

    suspend fun getRecommendedPlace(){
        _recommendedList.postValue(UiState.Loading)
        viewModelScope.launch {
            _recommendedList.postValue(mainUseCase.getRecommendedPlace())
        }
    }
}