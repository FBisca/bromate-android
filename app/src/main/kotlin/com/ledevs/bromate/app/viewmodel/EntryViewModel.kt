package com.ledevs.bromate.app.viewmodel

import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.data.model.Entry

sealed class EntryViewModel {

  companion object {
    fun createFrom(formatter: Formatter, entries: List<Entry>): List<EntryViewModel> {
      return entries
          .groupBy { formatter.format(it.date, Formatter.FORMAT_DAY_DESCRIPTION) }
          .flatMap {
            val (date, values) = it

            val header = EntryDateViewModel(date)
            val items = values.map { EntryRowViewModel(formatter, it) }

            listOf(header, *items.toTypedArray())
          }
    }
  }

  data class EntryDateViewModel(
      val date: String
  ) : EntryViewModel()

  class EntryRowViewModel(
      private val formatter: Formatter,
      private val entry: Entry
  ) : EntryViewModel() {
    fun getTitle() = entry.title
    fun getDescription() = entry.description
    fun getTotalValue() = "- ${formatter.formatCurrency(entry.totalValue)}"
    fun getChargeBackValue() = "+ ${formatter.formatCurrency(entry.chargeBackValue)}"
    fun getHour() = formatter.format(entry.date, Formatter.FORMAT_HOUR_DESCRIPTION)
  }

}
