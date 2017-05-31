package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.dependencies.formatter.LocaleFormatter
import com.ledevs.bromate.app.dependencies.formatter.StringFormatter
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import java.util.Date
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
  fun providesMoshi(stringFormatter: StringFormatter): Moshi {
    return Moshi.Builder()
        .add(object : Any() {
          @FromJson fun fromJson(value: String) = stringFormatter.parse(value, StringFormatter.FORMAT_TIMESTAMP)
          @ToJson fun toJson(value: Date?) = when (value) {
            null -> "null"
            else -> stringFormatter.format(value, StringFormatter.FORMAT_TIMESTAMP)
          }
        }).build()
  }
}
