package com.ledevs.bromate.app.ui.list.model

sealed class EntryListModel {

  data class EntryDateListModel(
      val date: String
  ) : EntryListModel()

  data class EntryRowListModel(
      val title: String,
      val description: String,
      val totalValue: String,
      val chargeBackValue: String,
      val hour: String
  ) : EntryListModel()

}
