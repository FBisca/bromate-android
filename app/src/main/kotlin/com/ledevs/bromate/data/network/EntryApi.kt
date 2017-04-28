package com.ledevs.bromate.data.network

import com.ledevs.bromate.data.model.Entry
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EntryApi {
  @GET("/entries")
  fun getEntries(@Query("month") month: String): Single<List<Entry>>
}