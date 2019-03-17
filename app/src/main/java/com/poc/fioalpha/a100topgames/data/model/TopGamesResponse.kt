package com.poc.fioalpha.a100topgames.data.model

import com.google.gson.annotations.SerializedName
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain

data class TopGamesResponse(
    @SerializedName("_total")
    val total: Int?,
    val top: List<ToResponse?>?
)

data class ToResponse(
    val channels: Int?,
    val game: GameResponse?,
    val viewers: Int?
)


data class LogoResponse(
    val large: String?,
    val medium: String?,
    val small: String?,
    val template: String?
)

data class GameResponse(
    @SerializedName("_id")
    val id: Int?,
    val box: HashMap<String,String>?,
    @SerializedName("giantbomb_id")
    val giantbombId: Int?,
    val logo: LogoResponse?,
    val name: String?,
    val popularity: Int?
)

data class BoxResponse(
    val large: String?,
    val medium: String?,
    val small: String?,
    val template: String?
)



fun TopGamesResponse.transformToDomain(): List<GamesDomain> {
    return top?.map { response ->
        GamesDomain(
            countChannel = response?.channels?: 0,
            countViews = response?.viewers?: 0,
            name = response?.game?.name?: "",
            image = response?.game?.box?: hashMapOf()
        )
    }?: arrayListOf()
}