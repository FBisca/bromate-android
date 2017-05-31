package com.ledevs.bromate.data.network

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.UserBalance
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface EntryApi {
  @GET
  fun getEntries(@Url url: String): Single<List<Entry>>

  @GET
  fun getUsersBalance(@Url url: String): Single<List<UserBalance>>
}
