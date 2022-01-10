package com.moralabs.hacooknew.data.home.remote.dto

import com.google.gson.annotations.SerializedName

data class FoodDTO(
    @SerializedName("id") var id : Int? = null,
    @SerializedName("title") var title : String? = null,
    @SerializedName("summary") var summary : String? = null,
    @SerializedName("dishTypes") var dishTypes : List<String>? = null,
    @SerializedName("image") var image : String? = null,
    @SerializedName("readyInMinutes") var readyInMinutes : Int? = null,
    @SerializedName("saved") var saved : Boolean? = null
)