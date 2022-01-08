package com.moralabs.hacooknew.data.home.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Recipe")
data class FoodEntity(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "title") val title : String?,
    @ColumnInfo(name = "summary") val summary : String?,
    @ColumnInfo(name = "dishTypes") val dishTypes : String?,
    @ColumnInfo(name = "image") val image : String?,
    @ColumnInfo(name = "readyInMinutes") val readyInMinutes : Int?,
    @ColumnInfo(name = "saved") val saved : Boolean?
)