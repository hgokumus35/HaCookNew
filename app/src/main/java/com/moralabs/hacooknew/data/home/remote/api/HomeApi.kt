package com.moralabs.hacooknew.data.home.remote.api

import com.moralabs.hacooknew.data.home.remote.dto.RandomFoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("recipes/random")
    suspend fun randomFoods(@Query("number") number : Int,
                    @Query("tags") tags : String,
                    @Query("limitLicense") limitLicense : Boolean,
                    @Query("apiKey") apiKey : String)
    : Response<RandomFoodResponse>
}