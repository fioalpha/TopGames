package com.poc.fioalpha.a100topgames.presentation.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.poc.fioalpha.a100topgames.BR
import com.poc.fioalpha.a100topgames.R
import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel
import com.poc.fioalpha.a100topgames.presentation.presenter.GamesTopPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class GameListActivity : AppCompatActivity(), GamesMainView {

    @Inject lateinit var presenter: GamesTopPresenter

    private val adapterGamesTop: GamesTopListAdapter = GamesTopListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        games_top_recycler.apply {
            this.adapter = adapterGamesTop
            layoutManager = LinearLayoutManager(this@GameListActivity)
        }
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
        adapterGamesTop.setData(data.toMutableList())
    }

    override fun showError() {
        Toast.makeText(this,  "Houve erro", Toast.LENGTH_SHORT).show()
    }
}

class GamesTopListAdapter: RecyclerView.Adapter<GamesTopListAdapter.GamesTopViewHolder>() {

    private val item: MutableList<GameViewModel> = arrayListOf()

    fun setData(data: MutableList<GameViewModel>) {
        item.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesTopViewHolder {
        val  viewBind = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.adapter_game_top, parent, false
        )
        return GamesTopViewHolder(viewBind)
    }

    override fun getItemCount(): Int = item.count()

    override fun onBindViewHolder(holder: GamesTopViewHolder, position: Int) {
        holder.bind(item[position])
    }

    class GamesTopViewHolder(
        private val itemDataBind: ViewDataBinding
    ): RecyclerView.ViewHolder(itemDataBind.root) {

        fun bind(item: GameViewModel) {
            itemDataBind.setVariable(BR.gamesTop, item)
            itemDataBind.executePendingBindings()
        }

    }

}

fun Toast.show(context: Context, message: String) {
    makeText(context, message, Toast.LENGTH_SHORT).show()
}