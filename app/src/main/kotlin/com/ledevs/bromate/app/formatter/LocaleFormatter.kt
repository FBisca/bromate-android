package com.ledevs.bromate.app.formatter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocaleFormatter(private val locale: Locale) : Formatter {
  override fun format(date: Date, format: String): String {
    return SimpleDateFormat(format, locale).format(date)
  }
}
