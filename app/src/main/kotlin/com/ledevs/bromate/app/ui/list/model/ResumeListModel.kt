package com.ledevs.bromate.app.ui.list.model

sealed class ResumeListModel {
  data class ResumeHeaderListModel(
      val valueToReceive: String,
      val valueOwned: String
  ) : ResumeListModel()

  data class ResumeUserListModel(
      val userName: String,
      val balance: String,
      val userImageUrl: String,
      val valueNegative: Boolean
  ) : ResumeListModel()
}
