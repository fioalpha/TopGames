package com.poc.fioalpha.a100topgames.di

import com.poc.fioalpha.a100topgames.presentation.presenter.GamesTopPresenter
import com.poc.fioalpha.a100topgames.presentation.presenter.GamesTopPresenterImpl
import com.poc.fioalpha.a100topgames.presentation.view.GameListActivity
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import dagger.Binds
import dagger.Module


@Module
abstract class GameTopsPresentationModule {

    @Binds abstract fun gamesTopViewProvides(gameListActivity: GameListActivity): GamesMainView

    @Binds abstract fun gemesTopPresenterProvides(gamesTopPresenter: GamesTopPresenterImpl): GamesTopPresenter
}