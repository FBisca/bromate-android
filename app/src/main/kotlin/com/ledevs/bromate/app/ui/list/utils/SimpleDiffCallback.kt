package com.ledevs.bromate.app.ui.list.utils

import android.support.v7.util.DiffUtil

class SimpleDiffCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>
): DiffUtil.Callback() {
  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition] == newList[newItemPosition]
  }

  override fun getOldListSize(): Int {
    return oldList.size
  }

  override fun getNewListSize(): Int {
    return newList.size
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return areItemsTheSame(oldItemPosition, newItemPosition)
  }
}