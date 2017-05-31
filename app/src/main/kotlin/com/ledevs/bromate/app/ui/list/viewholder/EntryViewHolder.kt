package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
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

      dataBinding.entryType.setImageDrawable(entity.icon)
      val portraitColor = ContextCompat.getColor(dataBinding.root.context, entity.iconPortraitColor)
      val iconColor = ContextCompat.getColor(dataBinding.root.context, entity.iconColor)

      DrawableCompat.wrap(dataBinding.entryType.background).apply {
        DrawableCompat.setTint(mutate(), portraitColor)
      }

      DrawableCompat.wrap(dataBinding.entryType.drawable).apply {
        DrawableCompat.setTint(mutate(), iconColor)
      }
    }
  }

  class EntryDateViewHolder(
      val dataBinding: RowEntryDateBinding
  ) : EntryViewHolder(dataBinding) {
    override fun bind(entity: EntryListModel) {
      dataBinding.entry = entity as EntryDateListModel
    }
  }
}
