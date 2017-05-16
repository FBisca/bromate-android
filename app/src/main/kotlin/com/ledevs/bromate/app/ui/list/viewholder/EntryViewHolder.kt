package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.app.viewmodel.EntryViewModel.EntryDateViewModel
import com.ledevs.bromate.app.viewmodel.EntryViewModel.EntryRowViewModel
import com.ledevs.bromate.databinding.RowEntryBinding
import com.ledevs.bromate.databinding.RowEntryDateBinding

sealed class EntryViewHolder(
    dataBinding: ViewDataBinding
) : BaseViewHolder<EntryViewModel>(dataBinding) {

  class EntryRowViewHolder(
      val dataBinding: RowEntryBinding
  ) : EntryViewHolder(dataBinding) {
    override fun bind(entity: EntryViewModel) {
      dataBinding.entry = entity as EntryRowViewModel
    }
  }

  class EntryDateViewHolder(
      val dataBinding: RowEntryDateBinding
  ) : EntryViewHolder(dataBinding) {
    override fun bind(entity: EntryViewModel) {
      dataBinding.entry = entity as EntryDateViewModel
    }
  }
}
