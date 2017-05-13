package com.ledevs.bromate.app.ui.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ledevs.bromate.app.ui.list.viewholder.EntryViewHolder
import com.ledevs.bromate.app.viewmodel.EntryViewModel

class EntryAdapter : RecyclerView.Adapter<EntryViewHolder<*>>() {

  companion object {
    const val VIEW_TYPE_ROW = 1
    const val VIEW_TYPE_DATE = 2
  }

  val items = mutableListOf<EntryViewModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder<*> {
    return when (viewType) {
      VIEW_TYPE_ROW -> EntryViewHolder.EntryRowViewHolder(parent)
      VIEW_TYPE_DATE -> EntryViewHolder.EntryDateViewHolder(parent)
      else -> TODO("ViewType $viewType not implemented")
    }
  }

  override fun onBindViewHolder(holder: EntryViewHolder<*>, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount() = items.size

  override fun getItemViewType(position: Int): Int {
    return when (items[position]) {
      is EntryViewModel.EntryRowViewModel -> VIEW_TYPE_ROW
      is EntryViewModel.EntryDateViewModel -> VIEW_TYPE_DATE
    }
  }
}
