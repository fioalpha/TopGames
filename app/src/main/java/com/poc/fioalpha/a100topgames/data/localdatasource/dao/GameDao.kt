package com.poc.fioalpha.a100topgames.data.localdatasource.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.poc.fioalpha.a100topgames.data.localdatasource.model.GameRoom
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface GameDao {

    @Query("SELECT * FROM game LIMIT :limit OFFSET :offset")
    fun getGame(offset:Int = 10, limit: Int = 10): Single<GameRoom>

    @Insert
    fun saveGame(games: List<GameRoom>): Completable

    @Delete
    fun deleGame(game: GameRoom): Completable

}