package com.moralabs.hacooknew.data.home.remote.dto

import com.google.gson.annotations.SerializedName

data class RandomFoodResponse(
    @SerializedName("recipes") var foods : List<FoodDTO>? = null
)