package com.ledevs.bromate.app.ui.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.app.ui.list.model.ResumeListModel.ResumeHeaderListModel
import com.ledevs.bromate.app.ui.list.model.ResumeListModel.ResumeUserListModel
import com.ledevs.bromate.app.ui.list.viewholder.ResumeViewHolder
import com.ledevs.bromate.app.ui.list.viewholder.ResumeViewHolder.ResumeHeaderViewHolder
import com.ledevs.bromate.app.ui.list.viewholder.ResumeViewHolder.ResumeUserViewHolder
import com.ledevs.bromate.databinding.RowResumeBinding
import com.ledevs.bromate.databinding.RowResumeUserBinding
import com.ledevs.bromate.extensions.layoutInflater

class ResumeAdapter : RecyclerView.Adapter<ResumeViewHolder>() {

  companion object {
    const val VIEW_TYPE_HEADER = 1
    const val VIEW_TYPE_USER = 2
  }

  val items = mutableListOf<ResumeListModel>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeViewHolder {
    val inflater = parent.layoutInflater()

    return when (viewType) {
      VIEW_TYPE_HEADER -> ResumeHeaderViewHolder(RowResumeBinding.inflate(inflater, parent, false))
      VIEW_TYPE_USER -> ResumeUserViewHolder(RowResumeUserBinding.inflate(inflater, parent, false))
      else -> TODO("ViewType $viewType not implemented")
    }
  }

  override fun onBindViewHolder(holder: ResumeViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount() = items.size

  override fun getItemViewType(position: Int): Int {
    return when (items[position]) {
      is ResumeHeaderListModel -> VIEW_TYPE_HEADER
      is ResumeUserListModel -> VIEW_TYPE_USER
    }
  }
}
