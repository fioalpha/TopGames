package com.poc.fioalpha.a100topgames.data.di

import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.data.RepositoryImpl
import com.poc.fioalpha.a100topgames.data.localdatasource.LocalDataSource
import com.poc.fioalpha.a100topgames.data.localdatasource.LocalDataSourceImpl
import com.poc.fioalpha.a100topgames.data.remotedatasource.RemoteDataSource
import com.poc.fioalpha.a100topgames.data.remotedatasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class DataModule {

    @Binds abstract fun remoteDataSourceProvides(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Binds abstract fun repositoryProvides(repository: RepositoryImpl): Repository

//    @Binds abstract fun localDataSourceProvides(localDataSource: LocalDataSourceImpl): LocalDataSource

}