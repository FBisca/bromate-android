package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.dependencies.scheduler.ApplicationThreadSchedulers
import com.ledevs.bromate.app.dependencies.scheduler.ThreadSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerModule {
  @Provides
  @Singleton
  fun providesThreadSchedulers(): ThreadSchedulers {
    return ApplicationThreadSchedulers()
  }
}
