package com.poc.fioalpha.a100topgames.presentation.view

import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel


interface GamesMainView {

    fun showLoading()
    fun hideLoading()
    fun setData(data: List<GameViewModel>)
    fun showError()
    fun goDetailGame(view: GameViewModel)

}