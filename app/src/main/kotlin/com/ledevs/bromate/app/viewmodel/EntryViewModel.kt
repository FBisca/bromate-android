package com.ledevs.bromate.app.viewmodel

import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import com.ledevs.bromate.app.dependencies.formatter.StringFormatter
import com.ledevs.bromate.app.dependencies.scheduler.ThreadSchedulers
import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.data.repository.EntryRepository
import io.reactivex.Single
import javax.inject.Inject

@VisibleForTesting
class EntryViewModel @Inject constructor(
    private val entryRepository: EntryRepository,
    private val formatter: StringFormatter,
    private val schedulers: ThreadSchedulers
) : ViewModel() {

  private var entries: List<EntryListModel> = emptyList()

  fun getEntries(): Single<List<EntryListModel>> {
    return when {
      entries.isNotEmpty() -> Single.just(entries)
      else -> requestEntries()
    }
  }

  private fun requestEntries(): Single<List<EntryListModel>> {
    return entryRepository.listOpenEntries()
        .map { EntryListModel.createFrom(formatter, it) }
        .doAfterSuccess { entries = it }
        .subscribeOn(schedulers.ioThread())
        .observeOn(schedulers.mainThread())
  }

}
