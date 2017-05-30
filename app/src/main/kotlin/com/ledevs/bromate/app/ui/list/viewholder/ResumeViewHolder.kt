package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.databinding.RowResumeBinding
import com.ledevs.bromate.databinding.RowResumeUserBinding

sealed class ResumeViewHolder(
    dataBinding: ViewDataBinding
) : BaseViewHolder<ResumeListModel>(dataBinding) {

  class ResumeHeaderViewHolder(
      val dataBinding: RowResumeBinding
  ) : ResumeViewHolder(dataBinding) {
    override fun bind(entity: ResumeListModel) {
      dataBinding.resume = entity as ResumeListModel.ResumeHeaderListModel
    }
  }

  class ResumeUserViewHolder(
      val dataBinding: RowResumeUserBinding
  ) : ResumeViewHolder(dataBinding) {
    override fun bind(entity: ResumeListModel) {

    }
  }

}
