package com.poc.fioalpha.a100topgames.core

import com.poc.fioalpha.a100topgames.di.SchedulerStrategies
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class SchedulerSrategiesTest: SchedulerStrategies(){

    override fun <T> applyScheduler(single: Single<T>): Single<T> {
        return single.observeOn(Schedulers.trampoline())
    }

}