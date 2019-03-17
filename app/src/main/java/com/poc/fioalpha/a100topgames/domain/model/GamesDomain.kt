package com.poc.fioalpha.a100topgames.domain.model


data class GamesDomain(
    val name: String,
    val image: HashMap<String, String>,
    val countChannel: Int,
    val countViews: Int
)