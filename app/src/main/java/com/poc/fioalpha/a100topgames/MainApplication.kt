package com.poc.fioalpha.a100topgames

import android.app.Activity
import android.app.Application
import com.poc.fioalpha.a100topgames.data.localdatasource.AppDataBase
import com.poc.fioalpha.a100topgames.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class MainApplication: Application(), HasActivityInjector{

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        buildTopLevelDependenciesGraph()


    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

    fun buildTopLevelDependenciesGraph() {
        DaggerAppComponent.builder().application(this)
            .build()
            .inject(this)
    }
}