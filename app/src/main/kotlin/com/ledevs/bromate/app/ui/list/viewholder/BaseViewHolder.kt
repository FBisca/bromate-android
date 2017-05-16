package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseViewHolder<in T>(
    viewDataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(
    viewDataBinding.root
) {
  abstract fun bind(entity: T)
}