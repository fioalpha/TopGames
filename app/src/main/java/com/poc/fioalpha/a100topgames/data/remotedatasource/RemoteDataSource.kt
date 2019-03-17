package com.poc.fioalpha.a100topgames.data.remotedatasource

import com.poc.fioalpha.a100topgames.data.model.TopGamesResponse
import com.poc.fioalpha.a100topgames.data.model.transformToDomain
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject


interface RemoteDataSource {

    fun getTopGames(page: Int = 0): Single<List<GamesDomain>>
}

class  RemoteDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): RemoteDataSource{

    override fun getTopGames(page: Int): Single<List<GamesDomain>> {
        return retrofit.create(RestService::class.java).getTopGames()
            .map { it.transformToDomain() }
    }

}