package com.poc.fioalpha.a100topgames.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{

    @Provides
    @Singleton
    fun applicationProvides(application: Application): Application = application

}