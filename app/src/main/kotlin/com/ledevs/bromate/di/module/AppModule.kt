package com.ledevs.bromate.di.module

import android.content.Context
import android.os.Build
import dagger.Module
import dagger.Provides
import java.util.Locale
import javax.inject.Singleton

@Module
class AppModule(
    private val context: Context
) {

  @Singleton
  @Provides
  fun context() = this.context

  @Singleton
  @Provides
  @Suppress("DEPRECATION")
  fun locale(): Locale {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      return this.context.resources.configuration.locales.get(0)
    } else {
      return this.context.resources.configuration.locale
    }
  }
}

