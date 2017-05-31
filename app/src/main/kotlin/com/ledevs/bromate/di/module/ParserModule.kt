package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.dependencies.parser.LocaleFormatter
import com.ledevs.bromate.app.dependencies.parser.MoshiDateAdapter
import com.ledevs.bromate.app.dependencies.parser.StringFormatter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.util.Locale
import javax.inject.Singleton

@Module
class ParserModule {
  @Singleton
  @Provides
  fun providesFormatter(locale: Locale): StringFormatter {
    return LocaleFormatter(locale)
  }

  @Singleton
  @Provides
  fun providesMoshi(stringFormatter: StringFormatter, dateAdapter: MoshiDateAdapter): Moshi {
    return Moshi.Builder()
        .add(dateAdapter)
        .build()
  }
}
