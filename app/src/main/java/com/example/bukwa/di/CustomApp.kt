package com.example.bukwa.di

import android.app.Application
import com.example.bukwa.di.appscope.DaggerAppComponent

class CustomApp: Application() {
    val appComponent by lazy{
   DaggerAppComponent.factory()
       .create(this)



    }
}