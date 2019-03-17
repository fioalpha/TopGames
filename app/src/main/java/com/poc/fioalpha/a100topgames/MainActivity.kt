package com.poc.fioalpha.a100topgames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.poc.fioalpha.a100topgames.data.Repository
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var re: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        re.getTopGames(10)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(this, " ljhfjkds", Toast.LENGTH_SHORT).show()
            },{

            })

    }
}
