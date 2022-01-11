package com.moralabs.hacooknew.presentation.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moralabs.hacooknew.domain.common.BaseResult
import com.moralabs.hacooknew.domain.entity.Food
import com.moralabs.hacooknew.domain.entity.HomeEntity
import com.moralabs.hacooknew.domain.usecase.HomeUseCase
import com.moralabs.hacooknew.presentation.home.HomeUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CollectionViewModel(private val homeUseCase : HomeUseCase) : ViewModel() {

    private val _collectionState : MutableStateFlow<CollectionUiState> = MutableStateFlow(CollectionUiState.Idle)
    val collectionState : StateFlow<CollectionUiState> = _collectionState

    fun getLists(){
        viewModelScope.launch {
            homeUseCase.execute()
                .onStart {
                    _collectionState.value = CollectionUiState.Loading
                }
                .catch { exception ->
                    _collectionState.value = CollectionUiState.Error(exception.message)
                }
                .collect { baseResult ->
                    when(baseResult){
                        is BaseResult.Success -> _collectionState.value = CollectionUiState.Success(baseResult.data)
                    }
                }
        }
    }

    fun fetch(){
        if(_collectionState.value != CollectionUiState.Loading){
            viewModelScope.launch {
                homeUseCase.nextRandomRecipe()
                    .onStart {
                        _collectionState.value = CollectionUiState.Loading
                    }
                    .catch { exception ->
                        _collectionState.value = CollectionUiState.Error(exception.message)
                    }
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> _collectionState.value = CollectionUiState.PageSuccess(baseResult.data)
                        }
                    }
            }
        }
    }
}

sealed class CollectionUiState {
    data class Success(val collectionEntity : HomeEntity) : CollectionUiState()
    data class Error(val error : String?) : CollectionUiState()
    data class PageSuccess(val foodList : List<Food>) : CollectionUiState()
    object Idle : CollectionUiState()
    object Loading : CollectionUiState()
}