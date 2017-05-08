package com.ledevs.bromate.app.formatter

import java.util.Date

interface Formatter {
  companion object {
    const val FORMAT_YEAR_MONTH = "yyyy-MM"
    const val FORMAT_DAY_DESCRIPTION = "EEE, dd"
    const val FORMAT_HOUR_DESCRIPTION = "HH:MM"
  }

  fun format(date: Date, format: String): String
}
