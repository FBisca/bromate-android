package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import com.ledevs.bromate.app.viewmodel.ResumeViewModel
import com.ledevs.bromate.databinding.RowResumeBinding
import com.ledevs.bromate.databinding.RowResumeUserBinding

sealed class ResumeViewHolder(
    dataBinding: ViewDataBinding
) : BaseViewHolder<ResumeViewModel>(dataBinding) {

  class ResumeHeaderViewHolder(
      val dataBinding: RowResumeBinding
  ) : ResumeViewHolder(dataBinding) {
    override fun bind(entity: ResumeViewModel) {

    }
  }

  class ResumeUserViewHolder(
      val dataBinding: RowResumeUserBinding
  ) : ResumeViewHolder(dataBinding) {
    override fun bind(entity: ResumeViewModel) {

    }
  }

}