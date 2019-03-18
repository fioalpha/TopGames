package com.poc.fioalpha.a100topgames.presentation.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import com.poc.fioalpha.a100topgames.R
import com.poc.fioalpha.a100topgames.data.Repository
import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel
import com.poc.fioalpha.a100topgames.presentation.presenter.GamesTopPresenter
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameListActivity : AppCompatActivity(), GamesMainView {

    @Inject lateinit var presenter: GamesTopPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.getGamesTops(0)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun setData(data: List<GameViewModel>) {

    }

    override fun showError() {
        Toast.makeText(this,  "Houve erro", Toast.LENGTH_SHORT).show()
    }
}

fun Toast.show(context: Context, message: String) {
    makeText(context, message, Toast.LENGTH_SHORT).show()
}