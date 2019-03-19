package com.poc.fioalpha.a100topgames.presentation.view

import android.content.Context
import android.content.Intent
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
import com.poc.fioalpha.a100topgames.presentation.view.DetailGameTopActivity.Companion.GAME_TOP_DATA_EXTRA
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class GameListActivity : AppCompatActivity(), GamesMainView {


    @Inject lateinit var presenter: GamesTopPresenter

    private val adapterGamesTop: GamesTopListAdapter = GamesTopListAdapter{
        presenter.selectedGame(it)
    }

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

    override fun goDetailGame(view: GameViewModel) {
        Intent(this, DetailGameTopActivity::class.java)
            .apply {
                putExtra(GAME_TOP_DATA_EXTRA, view)
                startActivity(this)
            }

    }

}

class GamesTopListAdapter(
    private val clickItem: (GameViewModel) -> Any
): RecyclerView.Adapter<GamesTopListAdapter.GamesTopViewHolder>() {

    private val item: MutableList<GameViewModel> = arrayListOf()

    fun setData(data: MutableList<GameViewModel>) {
        item.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesTopViewHolder {
        val  viewBind = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.adapter_game_top, parent, false
        )
        return GamesTopViewHolder(viewBind, clickItem)
    }

    override fun getItemCount(): Int = item.count()

    override fun onBindViewHolder(holder: GamesTopViewHolder, position: Int) {
        holder.bind(item[position])
    }

    class GamesTopViewHolder(
        private val itemDataBind: ViewDataBinding,
        private val clickItem: (GameViewModel) -> Any
    ): RecyclerView.ViewHolder(itemDataBind.root) {

        fun bind(item: GameViewModel) {
            itemDataBind.setVariable(BR.gamesTop, item)
            itemDataBind.executePendingBindings()
            itemDataBind.root.setOnClickListener { clickItem(item) }
        }
    }
}
