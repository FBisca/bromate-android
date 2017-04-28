package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.app.formatter.LocaleFormatter
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class ParserModule {
  @Singleton
  @Provides
  fun providesFormatter(locale: Locale): Formatter {
    return LocaleFormatter(locale)
  }
}