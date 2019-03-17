package com.poc.fioalpha.a100topgames.data.remotedatasource

import com.poc.fioalpha.a100topgames.data.model.TopGamesResponse
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject


interface RemoteDataSource {

    fun getTopGames(page: Int = 0): Single<TopGamesResponse>

}

class  RemoteDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): RemoteDataSource{
    override fun getTopGames(page: Int): Single<TopGamesResponse> {
        return retrofit.create(RestService::class.java).getTopGames()
    }

}