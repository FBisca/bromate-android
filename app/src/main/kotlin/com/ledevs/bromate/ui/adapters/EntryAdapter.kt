package com.ledevs.bromate.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.ledevs.bromate.ui.adapters.EntryAdapter.EntryViewHolder

class EntryAdapter : RecyclerView.Adapter<EntryViewHolder>() {

  val VIEW_TYPE_ROW = 1
  val VIEW_TYPE_DATE = 2

  private val items = mutableListOf<EntryViewModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
    return when (viewType) {
      VIEW_TYPE_ROW -> EntryViewHolder.EntryRowViewHolder(parent)
      VIEW_TYPE_DATE -> EntryViewHolder.EntryDateViewHolder(parent)
      else -> TODO("ViewType $viewType not implemented")
    }
  }

  override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount() = items.size

  override fun getItemViewType(position: Int): Int {
    return when (items[position]) {
      is EntryViewModel.EntryRowViewModel -> VIEW_TYPE_ROW
      is EntryViewModel.EntryDateViewModel -> VIEW_TYPE_DATE
    }
  }

  sealed class EntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(entryViewModel: EntryViewModel)

    class EntryRowViewHolder(viewParent: ViewParent) : EntryViewHolder() {
      override fun bind(entryViewModel: EntryViewModel) {

      }
    }
    class EntryDateViewHolder(viewParent: ViewParent) : EntryViewHolder()
  }

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
}