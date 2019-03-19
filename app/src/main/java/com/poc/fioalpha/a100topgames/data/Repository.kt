package com.poc.fioalpha.a100topgames.data

import com.poc.fioalpha.a100topgames.data.localdatasource.LocalDataSource
import com.poc.fioalpha.a100topgames.data.localdatasource.model.transformToGames
import com.poc.fioalpha.a100topgames.data.model.transformToDomain
import com.poc.fioalpha.a100topgames.data.remotedatasource.RemoteDataSource
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import io.reactivex.Single
import javax.inject.Inject


interface Repository {
    fun getTopGames(page: Int = 0, isConnected: Boolean): Single<List<GamesDomain>>
    fun isConnected(): Single<Boolean>
}

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val gamesMainView: GamesMainView
) : Repository {
    override fun isConnected(): Single<Boolean> {
        return localDataSource.isConnectedInternet()
    }

    override fun getTopGames(page: Int, isConnected: Boolean): Single<List<GamesDomain>> {
        return if (isConnected) {
            remoteDataSource.getTopGames(page)
                .doOnSuccess {
                    localDataSource.saveGame(it, page).blockingGet()
                }

        } else {
            localDataSource.getGames(offset = page)
        }
    }

}

