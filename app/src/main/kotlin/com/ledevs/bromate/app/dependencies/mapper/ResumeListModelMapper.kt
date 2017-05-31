package com.ledevs.bromate.app.dependencies.mapper

import android.support.annotation.VisibleForTesting
import com.ledevs.bromate.app.dependencies.parser.StringFormatter
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.data.model.UserBalance
import javax.inject.Inject

@VisibleForTesting
class ResumeListModelMapper @Inject constructor(
    private val formatter: StringFormatter
) : Mapper<List<UserBalance>, List<ResumeListModel>> {

  override fun convert(input: List<UserBalance>): List<ResumeListModel> {
    val (totalToReceive, totalOwned) = input.fold(0.0 to 0.0, { (totalToReceive, totalOwned), userBalance ->
      if (userBalance.balance < 0) {
        totalToReceive + userBalance.balance to totalOwned
      } else {
        totalToReceive to totalOwned + userBalance.balance
      }
    })

    val header = ResumeListModel.ResumeHeaderListModel(
        formatter.formatCurrency(totalToReceive),
        formatter.formatCurrency(totalOwned)
    )

    val userList = input.map { it.convertToListModel() }
    return listOf(header, *userList.toTypedArray())
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
