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
  private val parallaxFactor = 0.7f

  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    if (parent.adapter == null && parent.adapter.itemCount <= 0) {
      return
    }

    val viewHolder = parent.findViewHolderForAdapterPosition(0)
    val view = viewHolder?.itemView ?: return

    val rectTop = view.top * parallaxFactor
    val rectBottom = rectTop + view.height
    val rectLeft = parent.paddingLeft.toFloat()
    val rectRight = parent.width - parent.paddingRight.toFloat()

    canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, parallaxPaint)
  }
}