package com.ledevs.bromate.extensions

import android.view.LayoutInflater
import android.view.View

fun View.layoutInflater(): LayoutInflater {
  return LayoutInflater.from(context)
}
