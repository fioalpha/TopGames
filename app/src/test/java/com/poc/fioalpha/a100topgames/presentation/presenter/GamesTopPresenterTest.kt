package com.poc.fioalpha.a100topgames.presentation.presenter

import com.nhaarman.mockitokotlin2.*
import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test


class GamesTopPresenterTest {

    private val view: GamesMainView = mock()

    private val repository: Repository = mock()

    private lateinit var presenter: GamesTopPresenter

    @Before
    fun setup() {
        presenter = GamesTopPresenterImpl(view, repository)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(
            view,
            repository
        )
    }

    @Test
    fun `when called get gamestop with success request result show data` () {

        val data = getGamesDomain()

        whenever(repository.getTopGames(any())).thenReturn(Single.just(data))
        presenter.getGamesTops(1)

        inOrder(repository, view) {
            verify(view).showLoading()
            verify(repository).getTopGames(any())
            verify(view).setData(any())
            verify(view).hideLoading()
        }
    }

    @Test
    fun `when called get gamestop with error general result show error` () {
        whenever(repository.getTopGames(any())).thenReturn(Single.error(Exception()))
        presenter.getGamesTops(1)

        inOrder(repository, view) {
            verify(view).showLoading()
            verify(repository).getTopGames(any())
            verify(view).hideLoading()
            verify(view).showError()
        }
    }

    private fun getGamesDomain(): List<GamesDomain> = arrayListOf(
        GamesDomain("teste1", hashMapOf(Pair("url", "url")), 120, 123),
        GamesDomain("teste2", hashMapOf(Pair("url", "url")), 120, 123)
    )
}