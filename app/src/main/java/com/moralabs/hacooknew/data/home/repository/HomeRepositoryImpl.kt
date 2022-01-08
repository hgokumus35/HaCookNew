package com.moralabs.hacooknew.data.home.repository

import com.moralabs.hacooknew.data.home.HomeRepository
import com.moralabs.hacooknew.data.home.local.HomeDatabase
import com.moralabs.hacooknew.data.home.local.entity.FoodEntity
import com.moralabs.hacooknew.data.home.remote.api.HomeApi
import com.moralabs.hacooknew.domain.entity.Food

class HomeRepositoryImpl(private var homeApi : HomeApi, private var homeDatabase : HomeDatabase) : HomeRepository {
    override suspend fun getRandomFood(start : Int, end : Int) : List<Food>{
        val foodCount = homeDatabase.foodDao().count()

        if(end < foodCount){
            val foodList = homeDatabase.foodDao().getRandom(end - start)
            return foodList?.map {
                Food(
                    id = it.id,
                    title = it.title,
                    summary = it.summary,
                    dishTypes = it.dishTypes,
                    image = it.image,
                    readyInMinutes = it.readyInMinutes,
                    saved = it.saved
                )
            }
        }else{
            val response = homeApi.randomFoods(10, "", false, "4525ee62cfd1461eac516cd6472c5194")

            if(response.isSuccessful){
                response.body()?.foods?.map {
                    FoodEntity(
                        id = it.id ?: 0,
                        title = it.title,
                        summary = it.summary,
                        dishTypes = it.dishTypes,
                        image = it.image,
                        readyInMinutes = it.readyInMinutes,
                        saved = it.saved
                    )
                }?.let {
                    homeDatabase.foodDao().inserAll(it)
                }
                return response.body()?.foods?.map {
                    Food(
                        id = it.id,
                        title = it.title,
                        summary = it.summary,
                        dishTypes = it.dishTypes,
                        image = it.image,
                        readyInMinutes = it.readyInMinutes,
                        saved = it.saved
                    )
                } ?: listOf()
            }
        }
        return listOf()
    }

    override suspend fun getCollections(page : Int) : List<Food> = homeDatabase.foodDao().getCollections().map {
        Food(
            id = it.id,
            title = it.title,
            summary = it.summary,
            dishTypes = it.dishTypes,
            image = it.image,
            readyInMinutes = it.readyInMinutes,
            saved = it.saved
        )
    }



}











