package com.ledevs.bromate.test

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.EntryType
import java.util.Date

object ModelFabricator {

  fun entry(
      title: String = "Title",
      description: String = "Description",
      totalValue: Double = 10.0,
      chargeBackValue: Double = 5.0,
      date: Date = Date(),
      type: EntryType = EntryType.GROCERIES
  ) = Entry(
      title,
      description,
      totalValue,
      chargeBackValue,
      date,
      type
  )
}
