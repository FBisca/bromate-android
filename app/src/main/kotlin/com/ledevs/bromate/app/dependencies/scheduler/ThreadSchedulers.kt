package com.ledevs.bromate.app.dependencies.scheduler

import io.reactivex.Scheduler

interface ThreadSchedulers {
  fun mainThread(): Scheduler
  fun ioThread(): Scheduler
}
