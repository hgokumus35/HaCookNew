package com.moralabs.hacooknew.data.home

import com.moralabs.hacooknew.domain.entity.Food

interface HomeRepository {
    suspend fun getTodaysFood() : Food?
    suspend fun getRandomFood(start : Int, end : Int) : List<Food>
    suspend fun getCollections(page : Int) : List<Food>
    suspend fun getFilteredList(search : String) : List<Food>
    suspend fun addFood(food : Food) : Food
}