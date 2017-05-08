package com.ledevs.bromate.app.viewmodel

import android.graphics.drawable.Drawable
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
            val items = values.map { createFrom(formatter, it) }

            listOf(header, *items.toTypedArray())
          }
    }

    fun createFrom(formatter: Formatter, entry: Entry): EntryRowViewModel {
      return EntryRowViewModel(
          entry.value.toString(),
          entry.title,
          entry.description,
          formatter.format(entry.date, Formatter.FORMAT_HOUR_DESCRIPTION),
          1,
          null
      )
    }
  }

  data class EntryDateViewModel(
      val date: String
  ) : EntryViewModel()

  data class EntryRowViewModel(
      val value: String,
      val title: String,
      val description: String,
      val hour: String,
      val type: Int,
      val image: Drawable?
  ) : EntryViewModel()

}
