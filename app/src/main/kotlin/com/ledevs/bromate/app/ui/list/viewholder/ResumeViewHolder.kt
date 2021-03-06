package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.databinding.RowResumeBinding
import com.ledevs.bromate.databinding.RowResumeUserBinding

sealed class ResumeViewHolder(
    dataBinding: ViewDataBinding
) : BaseViewHolder<ResumeListModel>(dataBinding) {

  class ResumeHeaderViewHolder(
      val dataBinding: RowResumeBinding
  ) : ResumeViewHolder(dataBinding) {

    init {
      val drawable = DrawableCompat.wrap(dataBinding.imageLogo.drawable)
      val color = ContextCompat.getColor(dataBinding.root.context, android.R.color.white)
      DrawableCompat.setTint(drawable.mutate(), color)
    }

    override fun bind(entity: ResumeListModel) {
      dataBinding.resume = entity as ResumeListModel.ResumeHeaderListModel
      dataBinding.executePendingBindings()
    }
  }

  class ResumeUserViewHolder(
      val dataBinding: RowResumeUserBinding
  ) : ResumeViewHolder(dataBinding) {
    override fun bind(entity: ResumeListModel) {
      dataBinding.userInfo = entity as ResumeListModel.ResumeUserListModel
      dataBinding.executePendingBindings()
    }
  }

}
