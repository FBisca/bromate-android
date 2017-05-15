package com.ledevs.bromate.app.ui.list.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.ledevs.bromate.R

class ResumeItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

  private val parallaxPaint = Paint().apply {
    style = Paint.Style.FILL
    color = ContextCompat.getColor(context, R.color.colorPrimary)
  }

  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    if (parent.childCount > 0) {
      state.
      val child = parent.getChildAt(0)

      val rectTop =

      child
    }
  }
}