package com.example.bukwa.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(element: T)

    @Delete
    fun delete(element: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(element: T)
}