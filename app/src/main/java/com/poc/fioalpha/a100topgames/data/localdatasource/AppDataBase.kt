package com.poc.fioalpha.a100topgames.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poc.fioalpha.a100topgames.data.localdatasource.dao.GameDao
import com.poc.fioalpha.a100topgames.data.localdatasource.model.GameRoom


@Database(
    entities = [
        GameRoom::class
    ],
    version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}