package com.poc.fioalpha.a100topgames.data.remotedatasource

import com.poc.fioalpha.a100topgames.BuildConfig
import com.poc.fioalpha.a100topgames.data.model.TopGamesResponse
import dagger.multibindings.IntoMap
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface RestService {

    @Headers(
        "Client-id:${BuildConfig.TWITCH_API}"
    )
    @GET("top")
    fun getTopGames(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 10
    ): Single<TopGamesResponse>

}