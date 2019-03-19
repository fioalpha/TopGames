package com.poc.fioalpha.a100topgames.data.localdatasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain

@Entity(tableName = "game")
data class GameRoom (
    @PrimaryKey var _id: Int = 0,
    val name: String,
    val counter: Int,
    val viewers: Int,
    val image: String
)


fun GameRoom.transformToGame(): GamesDomain {
    return GamesDomain(
        name = this.name,
        countViews = viewers,
        countChannel = counter,
        image = hashMapOf()
    )
}

fun transformToGames(games: List<GameRoom>): List<GamesDomain> {
    return games.map { it.transformToGame() }
}

fun GamesDomain.transformToGame(index: Int): GameRoom {
    return GameRoom(
        _id = index,
        name = name,
        counter = countChannel,
        viewers = countViews,
        image = image["large"]?: ""
    )
}

fun GamesDomain.transformToGame(): GameRoom {
    return GameRoom(
        name = name,
        counter = countChannel,
        viewers = countViews,
        image = image["large"]?: ""
    )
}

