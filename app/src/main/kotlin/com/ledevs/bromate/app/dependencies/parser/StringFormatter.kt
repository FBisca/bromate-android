package com.ledevs.bromate.app.dependencies.parser

import java.util.Date

interface StringFormatter {
  companion object {
    const val FORMAT_DAY_DESCRIPTION = "EEEE, dd"
    const val FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss"
  }

  fun format(date: Date, format: String): String
  fun formatCurrency(value: Double): String
  fun parse(dateString: String, format: String): Date
  fun formatTime(date: Date): String
}
