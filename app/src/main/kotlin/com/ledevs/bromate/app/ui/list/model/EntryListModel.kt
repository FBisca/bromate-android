package com.ledevs.bromate.app.ui.list.model

import com.ledevs.bromate.app.dependencies.formatter.StringFormatter
import com.ledevs.bromate.data.model.Entry

sealed class EntryListModel {

  companion object {
    fun createFrom(formatter: StringFormatter, entries: List<Entry>): List<EntryListModel> {
      return entries
          .groupBy { formatter.format(it.date, StringFormatter.FORMAT_DAY_DESCRIPTION) }
          .flatMap {
            val (date, values) = it

            val header = EntryDateListModel(date)
            val items = values.map { EntryRowListModel(formatter, it) }

            listOf(header, *items.toTypedArray())
          }
    }
  }

  data class EntryDateListModel(
      val date: String
  ) : EntryListModel()

  class EntryRowListModel(
      private val formatter: StringFormatter,
      private val entry: Entry
  ) : EntryListModel() {
    fun getTitle() = entry.title
    fun getDescription() = entry.description
    fun getTotalValue() = "- ${formatter.formatCurrency(entry.totalValue)}"
    fun getChargeBackValue() = "+ ${formatter.formatCurrency(entry.chargeBackValue)}"
    fun getHour() = formatter.format(entry.date, StringFormatter.FORMAT_HOUR_DESCRIPTION)
  }

}
