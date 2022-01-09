package com.moralabs.hacooknew.data.home.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moralabs.hacooknew.data.home.local.entity.TodayFoodEntity

@Dao
interface TodayFoodDao {
    @Query("SELECT * FROM TodayRecipe WHERE day = :day LIMIT 1")
    fun get(day : String) : TodayFoodEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food : TodayFoodEntity)
}