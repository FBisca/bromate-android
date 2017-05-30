package com.ledevs.bromate.app.dependencies.formatter

import java.util.Date

interface StringFormatter {
  companion object {
    const val FORMAT_YEAR_MONTH = "yyyy-MM"
    const val FORMAT_DAY_DESCRIPTION = "EEEE, dd"
    const val FORMAT_HOUR_DESCRIPTION = "HH:MM"
  }

  fun format(date: Date, format: String): String
  fun formatCurrency(value: Double): String
}
