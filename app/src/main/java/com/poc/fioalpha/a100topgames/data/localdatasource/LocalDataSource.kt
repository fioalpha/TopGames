package com.poc.fioalpha.a100topgames.data.localdatasource

import com.poc.fioalpha.a100topgames.data.localdatasource.model.GameRoom
import com.poc.fioalpha.a100topgames.data.localdatasource.model.transformFromGames
import com.poc.fioalpha.a100topgames.data.localdatasource.model.transformToGame
import com.poc.fioalpha.a100topgames.data.localdatasource.model.transformToGames
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject


interface LocalDataSource {

    fun saveGame(games: List<GamesDomain>): Completable

    fun deleteGame(games: List<GamesDomain>): Completable

    fun getGames(limit: Int = 10, offset: Int): Single<List<GamesDomain>>

}

class LocalDataSourceImpl @Inject constructor(
    private val room: AppDataBase
): LocalDataSource{

    override fun saveGame(games: List<GamesDomain>): Completable {
        return Completable.defer {
            try {
                room.gameDao().saveGame(transformFromGames(games))
                Completable.complete()
            } catch (e: Exception) {
                Completable.error(e)
            }
        }
    }

    override fun deleteGame(games: List<GamesDomain>): Completable {
        return Completable.defer {
            try {
                games.forEach {
                    room.gameDao().deleteGame(it.transformToGame())
                }
                Completable.complete()
            } catch (e: Exception) {
                Completable.complete()
            }
        }
    }

    override fun getGames(limit: Int, offset: Int): Single<List<GamesDomain>> {
        return room.gameDao().getGame(limit, offset).map { transformToGames(it) }
    }
}