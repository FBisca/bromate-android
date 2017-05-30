package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.dependencies.formatter.StringFormatter
import com.ledevs.bromate.app.dependencies.formatter.LocaleFormatter
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class ParserModule {
  @Singleton
  @Provides
  fun providesFormatter(locale: Locale): StringFormatter {
    return LocaleFormatter(locale)
  }
}
