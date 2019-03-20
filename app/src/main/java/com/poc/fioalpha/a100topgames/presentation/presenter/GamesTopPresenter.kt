package com.poc.fioalpha.a100topgames.presentation.presenter

import com.poc.fioalpha.a100topgames.di.SchedulerStrategies
import com.poc.fioalpha.a100topgames.domain.usecase.GetGamesTopUseCase
import com.poc.fioalpha.a100topgames.domain.usecase.IsConnectedNetworkUseCase
import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel
import com.poc.fioalpha.a100topgames.presentation.model.transformToGamesDomainToViewModel
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

interface GamesTopPresenter {

    fun getGamesTops(page: Int)

    fun selectedGame(view: GameViewModel)
}

class GamesTopPresenterImpl @Inject constructor(
    private val gamesMainView: GamesMainView,
    private val isConnectedNetworkUseCase: IsConnectedNetworkUseCase,
    private val getGamesTopUseCase: GetGamesTopUseCase,
    private val scheduler: SchedulerStrategies
) : GamesTopPresenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun getGamesTops(page: Int) {
        gamesMainView.showLoading()
        disposable.add(
            isConnectedNetworkUseCase.execute()
                .doOnSuccess {
                    if(it.not()) gamesMainView.showNotConnected()
                    else gamesMainView.hideNotConnected()
                }
                .flatMap { getGamesTopUseCase.setConnected(it).setPage(page).execute() }
                .map { transformToGamesDomainToViewModel(it) }
                .compose { scheduler.applyScheduler(it) }
                .subscribe(
                    {
                        gamesMainView.setData(it)
                        gamesMainView.hideLoading()
                    },
                    {
                        gamesMainView.hideLoading()
                        gamesMainView.showError()
                    }
                )
        )
    }

    override fun selectedGame(view: GameViewModel) {
       gamesMainView.goDetailGame(view)
    }

}


