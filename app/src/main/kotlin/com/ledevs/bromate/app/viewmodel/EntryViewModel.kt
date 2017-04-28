package com.ledevs.bromate.app.viewmodel

import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.data.model.Entry

sealed class EntryViewModel {

  companion object {
    fun createFrom(formatter: Formatter, entries: List<Entry>): List<EntryViewModel> {
      return entries
          .groupBy { formatter.dateToDay(it.date) }
          .flatMap {
            val header = EntryDateViewModel(it.key)
            val items = it.value.map {
              EntryRowViewModel(it.value.toString(), it.title, it.description, 0)
            }

            listOf(header, *items.toTypedArray())
          }
    }
  }

  data class EntryDateViewModel(
      val date: String
  ) : EntryViewModel()

  data class EntryRowViewModel(
      val value: String,
      val title: String,
      val description: String,
      val type: Int
  ) : EntryViewModel()

}
