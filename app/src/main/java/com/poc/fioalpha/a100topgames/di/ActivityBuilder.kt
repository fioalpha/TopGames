package com.poc.fioalpha.a100topgames.di

import com.poc.fioalpha.a100topgames.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun mainActivity(): MainActivity
}