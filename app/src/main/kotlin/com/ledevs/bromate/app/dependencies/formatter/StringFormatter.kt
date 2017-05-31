package com.ledevs.bromate.app.dependencies.formatter

import java.util.Date

interface StringFormatter {
  companion object {
    const val FORMAT_YEAR_MONTH = "yyyy-MM"
    const val FORMAT_DAY_DESCRIPTION = "EEEE, dd"
    const val FORMAT_HOUR_DESCRIPTION = "HH:MM"
    const val FORMAT_TIMESTAMP = "yyyy-MM-dd HH:MM:ss"
  }

  fun format(date: Date, format: String): String
  fun formatCurrency(value: Double): String
  fun parse(dateString: String, format: String): Date
}
