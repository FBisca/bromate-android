package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.data.network.EntryApi
import com.ledevs.bromate.data.repository.EntryRepository
import com.ledevs.bromate.data.repository.EntryRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
  @Singleton
  @Provides
  fun providesEntryRepository(
      entryApi: EntryApi,
      formatter: Formatter
  ): EntryRepository {
    return EntryRepositoryImpl(entryApi, formatter)
  }
}