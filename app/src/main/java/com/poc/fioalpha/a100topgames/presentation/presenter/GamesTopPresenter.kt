package com.poc.fioalpha.a100topgames.presentation.presenter

import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel
import com.poc.fioalpha.a100topgames.presentation.model.transformToGamesDomainToViewModel
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface GamesTopPresenter {

    fun getGamesTops(page: Int)

    fun selectedGame(view: GameViewModel)
}

class GamesTopPresenterImpl @Inject constructor(
    private val gamesMainView: GamesMainView,
    private val repository: Repository
) : GamesTopPresenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun getGamesTops(page: Int) {
        var page1 =1
        if(page >= 10) page1 = page

        gamesMainView.showLoading()
        disposable.add(
            repository.getTopGames(page1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { transformToGamesDomainToViewModel(it) }
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