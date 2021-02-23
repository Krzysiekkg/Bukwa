package com.example.bukwa.di.fragmentScope

import androidx.fragment.app.Fragment
import com.example.bukwa.main.MainFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentMusicModule::class])
interface FragmentComponent {

    fun inject(mainFragment: MainFragment)

    @Subcomponent.Builder
    interface Builder{
        fun fragment(@BindsInstance fragment: Fragment):Builder

        fun build():FragmentComponent
    }
}