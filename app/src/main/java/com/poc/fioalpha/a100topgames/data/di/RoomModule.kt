package com.poc.fioalpha.a100topgames.data.di

import com.poc.fioalpha.a100topgames.data.localdatasource.AppDataBase
import com.poc.fioalpha.a100topgames.data.localdatasource.LocalDataSource
import com.poc.fioalpha.a100topgames.data.localdatasource.LocalDataSourceImpl
import dagger.Module
import dagger.Provides


@Module
class RoomModule {

    @Provides
    fun roomProvides(room: AppDataBase): LocalDataSource = LocalDataSourceImpl(room)

}