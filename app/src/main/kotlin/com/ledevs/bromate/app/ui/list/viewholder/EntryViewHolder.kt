package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.app.ui.list.model.EntryListModel.EntryDateListModel
import com.ledevs.bromate.app.ui.list.model.EntryListModel.EntryRowListModel
import com.ledevs.bromate.databinding.RowEntryBinding
import com.ledevs.bromate.databinding.RowEntryDateBinding

sealed class EntryViewHolder(
    dataBinding: ViewDataBinding
) : BaseViewHolder<EntryListModel>(dataBinding) {

  class EntryRowViewHolder(
      val dataBinding: RowEntryBinding
  ) : EntryViewHolder(dataBinding) {
    override fun bind(entity: EntryListModel) {
      dataBinding.entry = entity as EntryRowListModel
      dataBinding.executePendingBindings()
    }
  }

  class EntryDateViewHolder(
      val dataBinding: RowEntryDateBinding
  ) : EntryViewHolder(dataBinding) {
    override fun bind(entity: EntryListModel) {
      dataBinding.entry = entity as EntryDateListModel
      dataBinding.executePendingBindings()
    }
  }
}
