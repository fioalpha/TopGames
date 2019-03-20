package com.poc.fioalpha.a100topgames.data.localdatasource.dao

import androidx.room.*
import com.poc.fioalpha.a100topgames.data.localdatasource.model.GameRoom
import io.reactivex.Single


@Dao
interface GameDao {

    @Query("SELECT * FROM game LIMIT :limit OFFSET :offset")
    fun getGameWithLimit(offset:Int = 0, limit: Int = 10): Single<List<GameRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGame(games: List<GameRoom>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGame(games: GameRoom): Long

    @Delete
    fun deleteGame(game: GameRoom)

    @Query("SELECT * FROM game")
    fun getGameAll(): List<GameRoom>

}