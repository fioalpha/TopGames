package com.poc.fioalpha.a100topgames.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "https://api.twitch.tv/kraken/games/"
    }

    @Provides
    @Singleton
    fun restProvides(
        gson: Gson,
        client: OkHttpClient,
        @Named("BASE_URL") url: String
    ): Retrofit{
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(url)
            .build()
    }

    @Provides
    @Singleton
    fun gsonProvides(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun httpClientProvides(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor (
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun baseUrlProvides(): String = BASE_URL


}