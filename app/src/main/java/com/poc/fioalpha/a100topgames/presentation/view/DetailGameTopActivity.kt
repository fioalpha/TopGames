package com.poc.fioalpha.a100topgames.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.poc.fioalpha.a100topgames.R
import com.poc.fioalpha.a100topgames.databinding.ActivityDetailGameTopBinding
import com.poc.fioalpha.a100topgames.presentation.model.GameViewModel

class DetailGameTopActivity : AppCompatActivity() {

    companion object {
        const val GAME_TOP_DATA_EXTRA = "game_top_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = DataBindingUtil.setContentView<ActivityDetailGameTopBinding>(
            this,
            R.layout.activity_detail_game_top
        )

        val item: GameViewModel = intent.getSerializableExtra(GAME_TOP_DATA_EXTRA) as GameViewModel
        view.gamesTop = item

    }
}
