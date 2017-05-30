package com.ledevs.bromate.app.dependencies.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApplicationThreadSchedulers(
    private val mainThread: Scheduler = AndroidSchedulers.mainThread(),
    private val ioThread: Scheduler = Schedulers.io()
): ThreadSchedulers {

  override fun mainThread(): Scheduler {
    return mainThread
  }

  override fun ioThread(): Scheduler {
    return ioThread
  }
}
