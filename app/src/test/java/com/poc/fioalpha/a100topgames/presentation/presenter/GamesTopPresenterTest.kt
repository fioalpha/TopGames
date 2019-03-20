package com.poc.fioalpha.a100topgames.presentation.presenter

import com.nhaarman.mockitokotlin2.*
import com.poc.fioalpha.a100topgames.core.SchedulerSrategiesTest
import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.di.SchedulerStrategies
import com.poc.fioalpha.a100topgames.domain.model.GamesDomain
import com.poc.fioalpha.a100topgames.domain.usecase.GetGamesTopUseCase
import com.poc.fioalpha.a100topgames.domain.usecase.IsConnectedNetworkUseCase
import com.poc.fioalpha.a100topgames.presentation.view.GamesMainView
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test


class GamesTopPresenterTest {

    private val view: GamesMainView = mock()

    private val isConnectedUseCase: IsConnectedNetworkUseCase = mock()

    private val getGamesTopUseCase: GetGamesTopUseCase = mock()

    private lateinit var presenter: GamesTopPresenter

    @Before
    fun setup() {
        presenter = GamesTopPresenterImpl(
            view,
            isConnectedUseCase,
            getGamesTopUseCase,
            SchedulerSrategiesTest()
        )
    }

    @After
    fun tearDown() {
//        verifyNoMoreInteractions(
//            view,
//            isConnectedUseCase,
//            getGamesTopUseCase
//        )
    }

    @Test
    fun `when called get gamestop with success request result show data` () {

        val data = getGamesDomain()

        whenever(isConnectedUseCase.execute()).thenReturn(Single.just(true))

        whenever(getGamesTopUseCase.setConnected(true)).thenReturn(getGamesTopUseCase)
        whenever(getGamesTopUseCase.setPage(10)).thenReturn(getGamesTopUseCase)
        whenever(getGamesTopUseCase.execute()).thenReturn(Single.just(data))

        presenter.getGamesTops(10)

        inOrder(isConnectedUseCase, getGamesTopUseCase, view) {
            verify(view).showLoading()
            verify(isConnectedUseCase).execute()
            verify(view).hideNotConnected()
            verify(getGamesTopUseCase).setConnected(true)
            verify(getGamesTopUseCase).setPage(10)
            verify(getGamesTopUseCase).execute()
            verify(view).setData(any())
            verify(view).hideLoading()
        }
    }

    @Test
    fun `when called get gamestop without network` () {

        val data = getGamesDomain()

        whenever(isConnectedUseCase.execute()).thenReturn(Single.just(false))

        whenever(getGamesTopUseCase.setConnected(false)).thenReturn(getGamesTopUseCase)
        whenever(getGamesTopUseCase.setPage(10)).thenReturn(getGamesTopUseCase)
        whenever(getGamesTopUseCase.execute()).thenReturn(Single.just(data))

        presenter.getGamesTops(10)

        inOrder(isConnectedUseCase, getGamesTopUseCase, view) {
            verify(view).showLoading()
            verify(isConnectedUseCase).execute()
            verify(view).showNotConnected()
            verify(getGamesTopUseCase).setConnected(false)
            verify(getGamesTopUseCase).setPage(10)
            verify(getGamesTopUseCase).execute()
            verify(view).setData(any())
            verify(view).hideLoading()
        }
    }

    @Test
    fun `when called get gamestop error on request` () {
        whenever(isConnectedUseCase.execute()).thenReturn(Single.just(false))

        whenever(getGamesTopUseCase.setConnected(false)).thenReturn(getGamesTopUseCase)
        whenever(getGamesTopUseCase.setPage(10)).thenReturn(getGamesTopUseCase)
        whenever(getGamesTopUseCase.execute()).thenReturn(Single.error(Exception()))

        presenter.getGamesTops(10)

        inOrder(isConnectedUseCase, getGamesTopUseCase, view) {
            verify(view).showLoading()
            verify(isConnectedUseCase).execute()
            verify(view).showNotConnected()
            verify(getGamesTopUseCase).setConnected(false)
            verify(getGamesTopUseCase).setPage(10)
            verify(getGamesTopUseCase).execute()
            verify(view).hideLoading()
            verify(view).showError()
        }
    }

    private fun getGamesDomain(): List<GamesDomain> = arrayListOf(
        GamesDomain("teste1", "IMAGE", 120, 123),
        GamesDomain("teste2", "IMAGE", 120, 123)
    )
}
