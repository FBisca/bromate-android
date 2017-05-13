package com.ledevs.bromate.app.ui.list.viewholder

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseViewHolder<in T, out DB : ViewDataBinding>(
    protected val viewDataBinding: DB
) : RecyclerView.ViewHolder(
    viewDataBinding.root
) {
  abstract fun bind(entity: T)
}