package com.poc.fioalpha.a100topgames.data.localdatasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameRoom (
    @PrimaryKey val _id: Int,
    val counter: Int,
    val viewers: Int
)