package com.ledevs.bromate.app.ui.list.decorator

import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.ledevs.bromate.R
import java.util.concurrent.atomic.AtomicInteger

class ResumeItemDecorator(recyclerView: RecyclerView) : RecyclerView.ItemDecoration() {

  private val parallaxPaint = Paint().apply {
    style = Paint.Style.FILL
    color = ContextCompat.getColor(recyclerView.context, R.color.colorPrimary)
  }
  private val totalScrollY = AtomicInteger()
  private val parallaxFactor = 0.7f
  private var viewHeight = 0

  init {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        totalScrollY.addAndGet(dy)
      }
    })
  }

  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    if (parent.adapter == null || parent.adapter.itemCount <= 0) {
      return
    }

    val viewHolder = parent.findViewHolderForAdapterPosition(0)
    viewHeight = viewHolder?.itemView?.height ?: viewHeight

    val rectTop = -totalScrollY.get() * parallaxFactor
    val rectBottom = rectTop + viewHeight
    val rectLeft = parent.paddingLeft.toFloat()
    val rectRight = parent.width - parent.paddingRight.toFloat()

    canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, parallaxPaint)
  }
}
