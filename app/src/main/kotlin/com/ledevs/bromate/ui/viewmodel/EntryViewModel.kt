package com.ledevs.bromate.ui.viewmodel

sealed class EntryViewModel {

  data class EntryDateViewModel(
      val date: String
  ) : EntryViewModel()

  data class EntryRowViewModel(
      val value: String,
      val title: String,
      val description: String,
      val type: Int
  ) : EntryViewModel()

}
