package com.ledevs.bromate.test

import java.util.Calendar
import java.util.Date

object DateCreator {

  fun createDate(year: Int, month: Int, day: Int): Date {
    return Calendar.getInstance().let {
      it.clear()
      it.set(year, month, day)
      it.time
    }
  }

  fun createHour(hour: Int, minutes: Int): Date {
    return Calendar.getInstance().let {
      it.set(Calendar.HOUR_OF_DAY, hour)
      it.set(Calendar.MINUTE, minutes)
      it.time
    }
  }
}
