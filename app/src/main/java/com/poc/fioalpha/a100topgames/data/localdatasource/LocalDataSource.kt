package com.poc.fioalpha.a100topgames.data.localdatasource

import com.poc.fioalpha.a100topgames.data.localdatasource.model.transformToGame
import com.poc.fioalpha.a100topgames.data.localdatasource.model.transformToGames
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import io.reactivex.Completable
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject


interface LocalDataSource {

    fun saveGame(games: List<GamesDomain>, page: Int): Completable

    fun deleteGame(games: List<GamesDomain>): Completable

    fun getGames(limit: Int = 10, offset: Int): Single<List<GamesDomain>>


}

class LocalDataSourceImpl @Inject constructor(
    private val room: AppDataBase
): LocalDataSource{

    override fun saveGame(games: List<GamesDomain>, page: Int): Completable {
        return Completable.defer {
            try {
                val pageAdjustment = if(page > 1) page else 0
                games.forEachIndexed { index, gamesDomain ->
                    val id = index + pageAdjustment
                    room.gameDao().saveGame(gamesDomain.transformToGame(id))
                }
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
        val offsetAdjust = if(offset > 1) offset else 0
        return room.gameDao().getGameWithLimit(offsetAdjust, limit).map { transformToGames(it) }
    }
}