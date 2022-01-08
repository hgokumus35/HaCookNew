package com.moralabs.hacooknew.data.home.local.dao

import androidx.room.*
import com.moralabs.hacooknew.data.home.local.entity.FoodEntity

@Dao
interface FoodDao {
    @Query("SELECT * FROM Recipe")
    fun getAll() : List<FoodEntity>

    @Query("SELECT * FROM Recipe LIMIT :limit")
    fun getAll(limit : Int) : List<FoodEntity>

    @Query("SELECT * FROM Recipe ORDER BY RANDOM() LIMIT :limit")
    fun getRandom(limit : Int) : List<FoodEntity>

    @Query("SELECT count(*) FROM RECIPE")
    fun count() : Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserAll(recipe : List<FoodEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe : List<FoodEntity>)

    @Delete
    fun delete(recipe : FoodEntity)

    @Query("SELECT * FROM Recipe WHERE id = :id LIMIT 1")
    fun get(id : Int) : FoodEntity

    @Query("SELECT * FROM Recipe WHERE saved = 1")
    fun getCollections() : List<FoodEntity>
}