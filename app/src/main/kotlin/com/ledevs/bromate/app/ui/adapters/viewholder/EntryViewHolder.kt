package com.ledevs.bromate.app.ui.adapters.viewholder

import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.databinding.RowEntryBinding
import com.ledevs.bromate.databinding.RowEntryDateBinding

sealed class EntryViewHolder<out DB : ViewDataBinding>(
    viewDataBinding: DB
) : BaseViewHolder<EntryViewModel, DB>(viewDataBinding) {

  class EntryRowViewHolder(viewGroup: ViewGroup) : EntryViewHolder<RowEntryBinding>(
      RowEntryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
  ) {
    override fun bind(entity: EntryViewModel) {
      viewDataBinding.entry = entity as EntryViewModel.EntryRowViewModel
    }
  }

  class EntryDateViewHolder(viewGroup: ViewGroup) : EntryViewHolder<RowEntryDateBinding>(
      RowEntryDateBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
  ) {
    override fun bind(entity: EntryViewModel) {
      viewDataBinding.entry = entity as EntryViewModel.EntryDateViewModel?
    }

  }
}
