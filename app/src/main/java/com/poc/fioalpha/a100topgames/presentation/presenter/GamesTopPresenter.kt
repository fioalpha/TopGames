package com.poc.fioalpha.a100topgames.presentation.presenter

import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.presentation.model.transformToGamesDomainToViewModel
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface GamesTopPresenter {

    fun getGamesTops(page: Int)

}

class GamesTopPresenterImpl @Inject constructor(
    private val gamesMainView: GamesMainView,
    private val repository: Repository
) : GamesTopPresenter {

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun getGamesTops(page: Int) {
        gamesMainView.showLoading()
        disposable.add(
            repository.getTopGames(page)
                .map { transformToGamesDomainToViewModel(it) }
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
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
}