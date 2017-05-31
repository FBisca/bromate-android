package com.ledevs.bromate.data.repository

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.UserBalance
import io.reactivex.Single
import java.util.*

interface EntryRepository {
  fun listOpenEntries(): Single<List<Entry>>
  fun listUsersBalance(): Single<List<UserBalance>>
}