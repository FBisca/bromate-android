package com.ledevs.bromate.data.repository

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.UserBalance
import com.ledevs.bromate.data.network.EntryApi
import io.reactivex.Single

class EntryRepositoryImpl(
    private val entryApi: EntryApi
) : EntryRepository {

  override fun listOpenEntries(): Single<List<Entry>> {
    return entryApi.getEntries()
  }

  override fun listUsersBalance(): Single<List<UserBalance>> {
    return entryApi.getUsersBalance()
  }
}
