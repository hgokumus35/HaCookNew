package com.moralabs.hacooknew.data.home.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moralabs.hacooknew.data.home.local.dao.FoodDao
import com.moralabs.hacooknew.data.home.local.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
abstract class HomeDatabase : RoomDatabase(){
    abstract fun foodDao() : FoodDao
}