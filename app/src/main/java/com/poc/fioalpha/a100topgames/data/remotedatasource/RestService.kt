package com.poc.fioalpha.a100topgames.data.remotedatasource

import com.poc.fioalpha.a100topgames.data.model.TopGamesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface RestService {

    @GET("top")
    fun getTopGames(): Single<TopGamesResponse>

}