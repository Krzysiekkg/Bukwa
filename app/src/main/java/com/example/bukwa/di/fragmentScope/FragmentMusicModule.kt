package com.example.bukwa.di.fragmentScope

import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
object FragmentMusicModule {
    @JvmStatic
    @FragmentScope
    @Provides
    fun provideExecutor(): ExecutorService {
        return Executors.newSingleThreadExecutor()
    }

}