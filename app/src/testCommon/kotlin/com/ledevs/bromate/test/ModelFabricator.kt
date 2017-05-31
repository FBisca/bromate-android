package com.ledevs.bromate.test

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.EntryType
import com.ledevs.bromate.data.model.User
import com.ledevs.bromate.data.model.UserBalance
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

  fun user(
      name: String = "John Doe",
      email: String = "doedoe@gmail.com",
      imageUrl: String = "http://facebook.com/doe/profileimage"
  ) = User(
      name,
      email,
      imageUrl
  )

  fun userBalance(
      user: User = user(),
      balance: Double = 10.0
  ) = UserBalance(
      user,
      balance
  )
}
