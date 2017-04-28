package com.ledevs.bromate.app.formatter

import java.text.SimpleDateFormat
import java.util.*

class LocaleFormatter(locale: Locale) : Formatter {

  private val dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, locale)

  override fun dateToMonth(date: Date): String {
    return dateFormat.format(date)
  }

  override fun dateToDay(date: Date): String {
    return dateFormat.format(date)
  }

}