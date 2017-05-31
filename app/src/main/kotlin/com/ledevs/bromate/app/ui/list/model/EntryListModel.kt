package com.ledevs.bromate.app.ui.list.model

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes

sealed class EntryListModel {

  data class EntryDateListModel(
      val date: String
  ) : EntryListModel()

  data class EntryRowListModel(
      val title: String,
      val description: String,
      val totalValue: String,
      val chargeBackValue: String,
      val hour: String,
      @DrawableRes val icon: Int,
      @ColorRes val iconColor: Int,
      @ColorRes val iconPortraitColor: Int
  ) : EntryListModel()

}
