package com.ledevs.bromate.app.ui.adapters.viewholder

import android.support.annotation.LayoutRes
import android.view.ViewGroup
import com.ledevs.bromate.R
import com.ledevs.bromate.app.viewmodel.EntryViewModel

sealed class EntryViewHolder(
    @LayoutRes layoutRes: Int,
    viewGroup: ViewGroup
) : BaseViewHolder<EntryViewModel>(layoutRes, viewGroup) {

  class EntryRowViewHolder(viewGroup: ViewGroup) : EntryViewHolder(R.layout.row_entry, viewGroup) {
    override fun bind(entity: EntryViewModel) {

    }
  }

  class EntryDateViewHolder(viewGroup: ViewGroup) : EntryViewHolder(R.layout.row_entry_date, viewGroup) {
    override fun bind(entity: EntryViewModel) {

    }

  }
}
