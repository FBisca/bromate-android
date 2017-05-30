package com.ledevs.bromate.app.dependencies.formatter

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class LocaleFormatterTest {
  @Test
  fun `test_simple_date_format`() {
    val formatter = LocaleFormatter(Locale.ENGLISH)
    val calendar = Calendar.getInstance()
    calendar.set(1991, 2, 10, 12, 0, 0)

    val date = formatter.format(calendar.time, "yyyy/MM/dd")
    assertEquals("1991/03/10", date)
  }
}
