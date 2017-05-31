package com.ledevs.bromate.app.dependencies.mapper

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.VisibleForTesting
import com.ledevs.bromate.R
import com.ledevs.bromate.app.dependencies.parser.StringFormatter
import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.EntryType
import javax.inject.Inject

@VisibleForTesting
class EntryListModelMapper @Inject constructor(
    private val formatter: StringFormatter
) : Mapper<List<Entry>, List<EntryListModel>> {

  override fun convert(input: List<Entry>): List<EntryListModel> {
    return input.groupBy { formatter.format(it.date, StringFormatter.FORMAT_DAY_DESCRIPTION) }
        .flatMap {
          val (date, values) = it

          val header = EntryListModel.EntryDateListModel(date)
          val items = values.map { it.convertToListModel() }

          listOf(header, *items.toTypedArray())
        }
  }

  private fun Entry.convertToListModel() = EntryListModel.EntryRowListModel(
      title,
      description,
      "- ${formatter.formatCurrency(totalValue)}",
      formatter.formatCurrency(chargeBackValue),
      formatter.formatTime(date),
      iconRes(),
      R.color.white,
      iconBackgroundColor()
  )

  @DrawableRes
  private fun Entry.iconRes(): Int {
    return when (type) {
      EntryType.GROCERIES -> R.drawable.ic_local_grocery_store_black_24dp
      EntryType.FOOD -> R.drawable.ic_local_dining_black_24dp
      EntryType.HOME -> R.drawable.ic_domain_black_24dp
      EntryType.BILLS -> R.drawable.ic_account_balance_black_24dp
    }
  }

  @ColorRes
  private fun Entry.iconBackgroundColor(): Int {
    return when (type) {
      EntryType.GROCERIES -> R.color.bg_icon_groceries
      EntryType.FOOD -> R.color.bg_icon_food
      EntryType.HOME -> R.color.bg_icon_home
      EntryType.BILLS -> R.color.bg_icon_bill
    }
  }

}
