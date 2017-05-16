package com.ledevs.bromate.app.ui.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.list.viewholder.EntryViewHolder
import com.ledevs.bromate.app.ui.list.viewholder.EntryViewHolder.EntryDateViewHolder
import com.ledevs.bromate.app.ui.list.viewholder.EntryViewHolder.EntryRowViewHolder
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.app.viewmodel.EntryViewModel.EntryDateViewModel
import com.ledevs.bromate.app.viewmodel.EntryViewModel.EntryRowViewModel
import com.ledevs.bromate.databinding.RowEntryBinding
import com.ledevs.bromate.databinding.RowEntryDateBinding
import com.ledevs.bromate.extensions.layoutInflater

class EntryAdapter : RecyclerView.Adapter<EntryViewHolder>() {

  companion object {
    const val VIEW_TYPE_ROW = 1
    const val VIEW_TYPE_DATE = 2
  }

  val items = mutableListOf<EntryViewModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
    val inflater = parent.layoutInflater()

    return when (viewType) {
      VIEW_TYPE_ROW -> EntryRowViewHolder(RowEntryBinding.inflate(inflater, parent, false))
      VIEW_TYPE_DATE -> EntryDateViewHolder(RowEntryDateBinding.inflate(inflater, parent, false))
      else -> TODO("ViewType $viewType not implemented")
    }
  }

  override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount() = items.size

  override fun getItemViewType(position: Int): Int {
    return when (items[position]) {
      is EntryRowViewModel -> VIEW_TYPE_ROW
      is EntryDateViewModel -> VIEW_TYPE_DATE
    }
  }
}
