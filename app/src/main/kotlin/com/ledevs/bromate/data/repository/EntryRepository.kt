package com.ledevs.bromate.data.repository

import com.ledevs.bromate.data.model.Entry
import io.reactivex.Single
import java.util.*

interface EntryRepository {
  fun listOpenEntries(): Single<List<Entry>>
}