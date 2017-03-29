package com.ledevs.bromate.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(
    private val context: Context
) {

  @Provides
  fun context() = this.context
}
