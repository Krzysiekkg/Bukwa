package com.example.bukwa.di.appscope

import android.app.Application
import com.example.bukwa.data.RoomDatabaseModule
import com.example.bukwa.di.fragmentScope.FragmentComponent
import com.example.bukwa.viewmodelutils.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomDatabaseModule::class, ViewModelModule::class])
interface AppComponent {
   fun fragmentComponent():FragmentComponent.Builder

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}