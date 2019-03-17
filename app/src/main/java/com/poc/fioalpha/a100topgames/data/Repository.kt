package com.poc.fioalpha.a100topgames.data

import com.poc.fioalpha.a100topgames.data.model.transformToDomain
import com.poc.fioalpha.a100topgames.data.remotedatasource.RemoteDataSource
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import io.reactivex.Single
import javax.inject.Inject


interface Repository {
    fun getTopGames(page: Int = 0): Single<List<GamesDomain>>
}

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): Repository{

    override fun getTopGames(page: Int): Single<List<GamesDomain>> {
        return remoteDataSource.getTopGames(page)
            .map { it.transformToDomain() }
    }

}

