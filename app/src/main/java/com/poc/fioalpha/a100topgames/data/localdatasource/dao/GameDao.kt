package com.poc.fioalpha.a100topgames.data.localdatasource.dao

import androidx.room.*
import com.poc.fioalpha.a100topgames.data.localdatasource.model.GameRoom
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface GameDao {

    @Query("SELECT * FROM game LIMIT :limit OFFSET :offset")
    fun getGame(offset:Int = 10, limit: Int = 10): Single<List<GameRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGame(games: List<GameRoom>): List<Long>

    @Delete
    fun deleteGame(game: GameRoom)

}