package com.ledevs.bromate.di.module

import com.ledevs.bromate.data.network.EntryApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
  @Singleton
  @Provides
  fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://www.google.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Singleton
  @Provides
  fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient()
  }

  @Singleton
  @Provides
  fun providesEntryApi(retrofit: Retrofit): EntryApi {
    return retrofit.create(EntryApi::class.java)
  }
}
