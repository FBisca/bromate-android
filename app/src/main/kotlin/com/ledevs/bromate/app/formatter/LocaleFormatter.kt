package com.ledevs.bromate.app.formatter

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocaleFormatter(private val locale: Locale) : Formatter {

  private val currencyFormater = NumberFormat.getCurrencyInstance(locale)

  override fun format(date: Date, format: String): String {
    return SimpleDateFormat(format, locale).format(date)
  }

  override fun formatCurrency(value: Double): String {
    return currencyFormater.format(value)
  }
}
