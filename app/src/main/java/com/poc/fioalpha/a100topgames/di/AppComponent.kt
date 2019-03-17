package com.poc.fioalpha.a100topgames.di

import android.app.Application
import com.poc.fioalpha.a100topgames.MainActivity
import com.poc.fioalpha.a100topgames.MainApplication
import com.poc.fioalpha.a100topgames.data.di.DataModule
import com.poc.fioalpha.a100topgames.data.di.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        DataBaseModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)

}