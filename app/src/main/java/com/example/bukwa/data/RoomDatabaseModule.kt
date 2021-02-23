package com.example.bukwa.data

import android.app.Application
import androidx.room.Room
import com.example.bukwa.data.room.LettersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): MyDatabase {
        return Room.databaseBuilder(application, MyDatabase::class.java, "mydb.db")
            .build()
    }
    @Provides
    @Singleton
    fun provideLettersDao(room: MyDatabase) :LettersDao{
        return room.lettersDao()
    }


}