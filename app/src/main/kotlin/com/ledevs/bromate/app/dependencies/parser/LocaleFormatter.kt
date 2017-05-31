package com.ledevs.bromate.app.dependencies.parser

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocaleFormatter(private val locale: Locale) : StringFormatter {

  private val currencyFormatter = NumberFormat.getCurrencyInstance(locale)

  override fun format(date: Date, format: String): String {
    return SimpleDateFormat(format, locale).format(date)
  }

  override fun formatCurrency(value: Double): String {
    return currencyFormatter.format(value)
  }

  override fun parse(dateString: String, format: String): Date {
    return SimpleDateFormat(format, locale).parse(dateString)
  }
}
