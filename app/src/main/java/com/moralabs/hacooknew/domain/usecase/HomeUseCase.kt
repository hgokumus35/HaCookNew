package com.moralabs.hacooknew.domain.usecase

import com.moralabs.hacooknew.data.home.HomeRepository
import com.moralabs.hacooknew.data.home.remote.dto.RandomFoodResponse
import com.moralabs.hacooknew.domain.common.BaseResult
import com.moralabs.hacooknew.domain.entity.Food
import com.moralabs.hacooknew.domain.entity.HomeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HomeUseCase(private val homeRepository : HomeRepository) {
    val pageSize = 20
    var page = 0

    fun execute() : Flow<BaseResult<HomeEntity, RandomFoodResponse>> {
        return flow{
            emit(
                BaseResult.Success(
                    HomeEntity(
                        todaysFood = homeRepository.getTodaysFood(),
                        randomFood = mutableListOf(),
                        collections = mutableListOf()
                    )
                )
            )
        }
    }

    fun nextRandomRecipe() : Flow<BaseResult<List<Food>, RandomFoodResponse>>{
        ++page
        return flow{
            emit(
                BaseResult.Success(
                    homeRepository.getRandomFood(page * pageSize, (page + 1) * pageSize)
                )
            )
        }
    }

    fun getCollections(page : Int) : Flow<BaseResult<List<Food>, RandomFoodResponse>>{
        return flow{
            emit(
                BaseResult.Success(
                    homeRepository.getCollections(page)
                )
            )
        }
    }
}