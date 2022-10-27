package com.github.andrewhossam.udacitysession3.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoeDatabaseDao {
    @Insert
    suspend fun insertShoe(shoe: Shoe)

    @Update
    suspend fun update(night: Shoe)

    @Query("SELECT * from shoe WHERE id = :key")
    suspend fun get(key: Long): Shoe?

    @Query("Delete from shoe where id = :id")
    suspend fun deleteShoe(id: Long)

    @Query("Delete from shoe")
    suspend fun deleteAll()

    @Query("Select * from Shoe")
    fun getAll(): LiveData<List<Shoe>>
}