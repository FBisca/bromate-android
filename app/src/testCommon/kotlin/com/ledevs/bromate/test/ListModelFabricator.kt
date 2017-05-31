package com.ledevs.bromate.test

import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.app.ui.list.model.ResumeListModel

object ListModelFabricator {

  fun entryDateModel(
      date: String = "Monday, 10"
  ) = EntryListModel.EntryDateListModel(
     date
  )

  fun entryRowModel(
      title: String = "Title",
      description: String = "Description",
      totalValue: String = "-$10.0",
      chargeBackValue: String = "+$5.0",
      hour: String = "5:00PM"
  ) = EntryListModel.EntryRowListModel(
      title,
      description,
      totalValue,
      chargeBackValue,
      hour,
      1,
      1,
      1
  )

  fun resumeHeaderModel(
      valueToReceive: String = "$1.400.00",
      valueOwned: String = "$300.00"
  ) = ResumeListModel.ResumeHeaderListModel(
      valueToReceive,
      valueOwned
  )

  fun resumeUserModel(
      userName: String = "John Doe",
      balance: String = "-$1.100.00",
      userImageUrl: String = "http://68.media.tumblr.com/avatar_72f36b0f112e_128.png",
      negativeValue: Boolean = true
  ) = ResumeListModel.ResumeUserListModel(
      userName,
      balance,
      userImageUrl,
      negativeValue
  )

}
