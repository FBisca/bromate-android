package com.ledevs.bromate.app.viewmodel

import android.support.annotation.VisibleForTesting
import com.ledevs.bromate.app.dependencies.mapper.ResumeListModelMapper
import com.ledevs.bromate.app.dependencies.parser.StringFormatter
import com.ledevs.bromate.app.dependencies.scheduler.ThreadSchedulers
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.data.model.UserBalance
import com.ledevs.bromate.data.repository.EntryRepository
import io.reactivex.Single
import javax.inject.Inject

@VisibleForTesting
class ResumeViewModel @Inject constructor(
    private val entryRepository: EntryRepository,
    private val resumeListModelMapper: ResumeListModelMapper,
    private val schedulers: ThreadSchedulers
) : ViewModel {

  private var resumeList: List<ResumeListModel> = emptyList()

  fun getResume(): Single<List<ResumeListModel>> {
    return entryRepository.listUsersBalance()
        .map { resumeListModelMapper.convert(it) }
        .doAfterSuccess { resumeList = it }
        .subscribeOn(schedulers.ioThread())
        .observeOn(schedulers.mainThread())
  }
}
