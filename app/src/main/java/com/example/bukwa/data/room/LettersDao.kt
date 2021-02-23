package com.example.bukwa.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.bukwa.data.BaseDao

@Dao
interface LettersDao : BaseDao<Letters> {
    @Query("SELECT * FROM letters")
    fun getAllLetters() :LiveData<List<Letters>>

    @Query("SELECT * FROM letters WHERE id =:id")
    fun selectLetterById(id:Long) :LiveData<Letters>
}