package com.ledevs.bromate.ui.adapters.viewholder

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseViewHolder<in T>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
) {
  abstract fun bind(entity: T)
}