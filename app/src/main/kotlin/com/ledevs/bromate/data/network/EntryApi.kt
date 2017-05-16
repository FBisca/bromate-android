package com.ledevs.bromate.data.network

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.UserBalance
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EntryApi {
  @GET("/entries")
  fun getEntries(): Single<List<Entry>>

  @GET("/users/balance")
  fun getUsersBalance(): Single<List<UserBalance>>
}