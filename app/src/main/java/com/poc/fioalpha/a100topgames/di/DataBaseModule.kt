package com.poc.fioalpha.a100topgames.di

import android.app.Application
import androidx.room.Room
import com.poc.fioalpha.a100topgames.data.localdatasource.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun databaseProvides(context: Application): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "database.sql"
    ).allowMainThreadQueries().build()

}