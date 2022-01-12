package com.moralabs.hacooknew.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moralabs.hacooknew.domain.common.BaseResult
import com.moralabs.hacooknew.domain.entity.Food
import com.moralabs.hacooknew.domain.entity.HomeEntity
import com.moralabs.hacooknew.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Math.log

class HomeViewModel(private val homeUseCase : HomeUseCase) : ViewModel() {
    private val _homeState : MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeState : StateFlow<HomeUiState> = _homeState

    fun getLists(){
        viewModelScope.launch {
            homeUseCase.execute()
                .onStart {
                    _homeState.value = HomeUiState.Loading
                }
                .catch { exception ->
                    _homeState.value = HomeUiState.Error(exception.message)
                }
                .collect { baseResult ->
                    when(baseResult){
                        is BaseResult.Success -> _homeState.value = HomeUiState.Success(baseResult.data)
                    }
                }
        }
    }

    fun fetch(){
        if(_homeState.value != HomeUiState.Loading){
            viewModelScope.launch {
                homeUseCase.nextRandomRecipe()
                    .onStart {
                        _homeState.value = HomeUiState.Loading
                    }
                    .catch { exception ->
                        _homeState.value = HomeUiState.Error(exception.message)
                    }
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> _homeState.value = HomeUiState.PageSuccess(baseResult.data)
                        }
                    }
            }
        }
    }

    fun getCollections(){
        viewModelScope.launch {
            homeUseCase.getCollections(0)
                .onStart {
                    _homeState.value = HomeUiState.Loading
                }
                .catch { exception ->
                    _homeState.value = HomeUiState.Error(exception.message)
                }
                .collect { baseResult ->
                    when(baseResult){
                        is BaseResult.Success -> _homeState.value = HomeUiState.PageSuccess(baseResult.data)
                    }
                }
        }
    }

    fun addFood(food : Food){
            viewModelScope.launch {
                homeUseCase.addFood(food)
                    .onStart {
                        _homeState.value = HomeUiState.Loading
                    }
                    .catch { exception ->
                        _homeState.value = HomeUiState.Error(exception.message)
                    }
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> _homeState.value = HomeUiState.Inserted(baseResult.data)
                        }
                    }
            }
        }
}

sealed class HomeUiState{
    data class Success(val homeEntity : HomeEntity) : HomeUiState()
    data class Inserted(val food : Food) : HomeUiState()
    data class Error(val error : String?) : HomeUiState()
    data class PageSuccess(val foodList : List<Food>) : HomeUiState()
    object Idle : HomeUiState()
    object Loading : HomeUiState()
}