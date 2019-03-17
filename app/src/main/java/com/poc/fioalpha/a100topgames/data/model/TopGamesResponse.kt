package com.poc.fioalpha.a100topgames.data.model

import com.google.gson.annotations.SerializedName

data class TopGamesResponse(
    @SerializedName("_total")
    val total: Int?,
    val top: List<Any?>?
)