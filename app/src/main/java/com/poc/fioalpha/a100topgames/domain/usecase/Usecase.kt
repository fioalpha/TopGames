package com.poc.fioalpha.a100topgames.domain.usecase

import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import io.reactivex.Single
import javax.inject.Inject


interface UseCaseBase <T> {
    fun  execute(): T
}

interface IsConnectedNetworkUseCase: UseCaseBase<Single<Boolean>>

class IsConnectedNetworkUseCaseImpl @Inject constructor(
    private val repository: Repository
): IsConnectedNetworkUseCase{

    override fun execute(): Single<Boolean> {
        return repository.isConnected()
    }

}

interface GetGamesTopUseCase: UseCaseBase<Single<List<GamesDomain>>> {
    fun setPage(page: Int): GetGamesTopUseCase
    fun setConnected(isConnected: Boolean): GetGamesTopUseCase
}

class GetGamesTopUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetGamesTopUseCase{

    private var page: Int = 0

    private var isConnected: Boolean = true

    override fun setConnected(isConnected: Boolean) = apply {
        this.isConnected = isConnected
    }

    override fun setPage(page: Int) = apply {
        this.page = page
    }

    override fun execute(): Single<List<GamesDomain>> {
        if (page > 100) return Single.just(emptyList())
        return repository.getTopGames(page, isConnected)
    }
}