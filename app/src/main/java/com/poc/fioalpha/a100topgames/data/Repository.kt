package com.poc.fioalpha.a100topgames.data

import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import io.reactivex.Single


interface Repository {
    fun getTopGames(page: Int = 0): Single<List<GamesDomain>>
}