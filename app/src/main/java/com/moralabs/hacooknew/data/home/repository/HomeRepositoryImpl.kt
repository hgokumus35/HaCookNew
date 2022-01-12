package com.moralabs.hacooknew.data.home.repository

import com.moralabs.hacooknew.data.home.HomeRepository
import com.moralabs.hacooknew.data.home.local.HomeDatabase
import com.moralabs.hacooknew.data.home.local.entity.FoodEntity
import com.moralabs.hacooknew.data.home.local.entity.TodayFoodEntity
import com.moralabs.hacooknew.data.home.remote.api.HomeApi
import com.moralabs.hacooknew.domain.entity.Food
import java.text.SimpleDateFormat
import java.util.*

class HomeRepositoryImpl(private var homeApi : HomeApi, private var homeDatabase : HomeDatabase) : HomeRepository {

    override suspend fun getTodaysFood(): Food? {
        val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
        var localFood : FoodEntity? = null

        homeDatabase.todayFoodDao().get(currentDate)?.recipeId?.let {
            localFood = homeDatabase.foodDao().get(it)
        }

        if(localFood == null){
            val response = homeApi.randomFoods(1, "", false, "4525ee62cfd1461eac516cd6472c5194")

            if(response.isSuccessful){
                val foodDTO = response.body()?.foods?.get(0)

                localFood = foodDTO?.run {
                    FoodEntity(
                        id = this.id ?: 0,
                        title = this.title,
                        summary = this.summary,
                        dishTypes = this.dishTypes?.joinToString(","),
                        image = this.image,
                        readyInMinutes = this.readyInMinutes,
                        saved = this.saved
                    )
                }

                localFood?.let {
                    homeDatabase.foodDao().insert(it)
                    homeDatabase.todayFoodDao().insert(it.run {
                        TodayFoodEntity(
                            day = currentDate,
                            recipeId = it.id
                        )
                    })
                }
            }
        }

        return localFood?.run {
            Food(
                id = this.id,
                title = this.title,
                summary = this.summary,
                dishTypes = this.dishTypes,
                image = this.image,
                readyInMinutes = this.readyInMinutes,
                saved = this.saved
            )
        }
    }

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
                        dishTypes = it.dishTypes?.joinToString(","),
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
                        dishTypes = it.dishTypes?.joinToString(","),
                        image = it.image,
                        readyInMinutes = it.readyInMinutes,
                        saved = it.saved
                    )
                } ?: listOf()
            }
        }
        return listOf()
    }

    override suspend fun getCollections(page : Int) : List<Food> {
        val list: List<FoodEntity> = homeDatabase.foodDao().getCollections()
        return list.map {
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

    override suspend fun getFilteredList(search : String) : List<Food>{
        val list : List<FoodEntity> = homeDatabase.foodDao().getFilteredList(search)
        return list.map {
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

    override suspend fun addFood(food : Food) : Food {
        food?.let {
            homeDatabase.foodDao().insert(it.run { FoodEntity(
                id = this.id!!,
                title = this.title,
                summary = this.summary,
                dishTypes = this.dishTypes,
                image = this.image,
                readyInMinutes = this.readyInMinutes,
                saved = this.saved
            ) })
        }
        return food
    }
}











