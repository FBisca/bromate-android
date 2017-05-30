package com.ledevs.bromate.app.ui.list.model

sealed class ResumeListModel {
  class ResumeHeaderListModel : ResumeListModel()
  class ResumeUserListModel : ResumeListModel()
}
