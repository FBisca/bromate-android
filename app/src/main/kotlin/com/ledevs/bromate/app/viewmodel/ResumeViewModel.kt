package com.ledevs.bromate.app.viewmodel

import android.support.annotation.VisibleForTesting
import com.ledevs.bromate.app.dependencies.formatter.StringFormatter
import com.ledevs.bromate.app.dependencies.scheduler.ThreadSchedulers
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.data.model.UserBalance
import com.ledevs.bromate.data.repository.EntryRepository
import io.reactivex.Single
import javax.inject.Inject

@VisibleForTesting
class ResumeViewModel @Inject constructor(
    private val entryRepository: EntryRepository,
    private val formatter: StringFormatter,
    private val schedulers: ThreadSchedulers
) : ViewModel {

  private var resumeList: List<ResumeListModel> = emptyList()

  fun getResume(): Single<List<ResumeListModel>> {
    return entryRepository.listUsersBalance()
        .map { it.convertToListModel() }
        .doAfterSuccess { resumeList = it }
        .subscribeOn(schedulers.ioThread())
        .observeOn(schedulers.mainThread())
  }

  private fun List<UserBalance>.convertToListModel(): List<ResumeListModel> {
    val totalToReceive = fold(0.0, { acc, userBalance ->
      if (userBalance.balance < 0) acc + userBalance.balance else acc
    })

    val totalOwned = fold(0.0, { acc, userBalance ->
      if (userBalance.balance > 0) acc + userBalance.balance else acc
    })

    val header = ResumeListModel.ResumeHeaderListModel(
        formatter.formatCurrency(totalToReceive),
        formatter.formatCurrency(totalOwned)
    )

    val userList = map { it.convertToListModel() }.toTypedArray()
    return listOf(header, *userList)
  }

  private fun UserBalance.convertToListModel(): ResumeListModel.ResumeUserListModel {
    return ResumeListModel.ResumeUserListModel(
        user.name,
        formatter.formatCurrency(balance),
        user.imageUrl,
        balance < 0
    )
  }
}
