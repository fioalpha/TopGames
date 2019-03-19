package com.poc.fioalpha.a100topgames.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.snackbar.Snackbar
import com.poc.fioalpha.a100topgames.BR
import com.poc.fioalpha.a100topgames.R
import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel
import com.poc.fioalpha.a100topgames.presentation.presenter.GamesTopPresenter
import com.poc.fioalpha.a100topgames.presentation.view.DetailGameTopActivity.Companion.GAME_TOP_DATA_EXTRA
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.annotations.NotNull
import javax.inject.Inject

class GameListActivity : AppCompatActivity(), GamesMainView {


    @Inject lateinit var presenter: GamesTopPresenter

    private val gridLayoutManager = GridLayoutManager(this@GameListActivity, 2)

    private var scrollEndLess = EndLessScroll(
        gridLayoutManager,
        EndLessStrategies.create()
    ){
        presenter.getGamesTops(it)
    }

    private val adapterGamesTop: GamesTopListAdapter = GamesTopListAdapter{
        presenter.selectedGame(it)
    }

    private val snackbar: Snackbar by lazy {
        Snackbar.make(games_top_recycler, "Sem conexao a internet", Snackbar.LENGTH_INDEFINITE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        games_top_recycler.apply {
            this.adapter = adapterGamesTop
            layoutManager = gridLayoutManager
            addOnScrollListener(scrollEndLess)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.getGamesTops(0)
    }

    override fun showLoading() {
        game_top_loading.show()
    }

    override fun hideLoading() {
        game_top_loading.hide()
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

    override fun showNotConnected() {
        if (snackbar.isShown.not()) snackbar.show()
    }

    override fun hideNotConnected() {
        snackbar.dismiss()
    }

}

class EndLessScroll(
    private val layoutManager: GridLayoutManager,
    private val endLessStrategies: EndLessStrategies,
    private val updateAction: (Int) -> Any
): RecyclerView.OnScrollListener(){
    var totalItemCount = 0
    var lastItemCount = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        totalItemCount = layoutManager.itemCount
        lastItemCount = layoutManager.findLastCompletelyVisibleItemPosition() + 1

        if (endLessStrategies.isUpdateData(totalItemCount, lastItemCount)) {
            updateAction(totalItemCount)
        }
    }
}

class EndLessStrategies {

    fun isUpdateData(
        totalItemCount: Int,
        lastItemVisible: Int
    ): Boolean {
        return (lastItemVisible) == totalItemCount
    }

    companion object {
        fun create() = EndLessStrategies()
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

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(@NotNull url: String) {
    if(url.isEmpty()) return
    Picasso.with(context).load(url)
        .into(this)
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}