package com.example.bukwa.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.example.bukwa.di.appscope.DaggerAppComponent

class CustomApp: Application() {
    val appComponent by lazy{
        Glide.get(this)
            .setMemoryCategory(MemoryCategory.HIGH)

   DaggerAppComponent.factory()
       .create(this)
    }

}
