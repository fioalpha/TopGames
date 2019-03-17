package com.poc.fioalpha.a100topgames.di

import com.poc.fioalpha.a100topgames.MainActivity
import com.poc.fioalpha.a100topgames.data.di.DataModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
            DataModule::class
        ]
    )
    abstract fun mainActivity(): MainActivity
}