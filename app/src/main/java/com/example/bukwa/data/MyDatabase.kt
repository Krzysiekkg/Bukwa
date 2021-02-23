package com.example.bukwa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bukwa.data.room.Letters
import com.example.bukwa.data.room.LettersDao
import javax.inject.Singleton

@Singleton
@Database(entities = arrayOf(Letters::class), version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun lettersDao(): LettersDao
}