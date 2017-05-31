package com.ledevs.bromate.app.ui.list.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.list.EntryAdapter

class EntryItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

  private val backgroundFirst = ContextCompat.getDrawable(context, R.drawable.row_entry_background_first)
  private val backgroundLast = ContextCompat.getDrawable(context, R.drawable.row_entry_background_last)
  private val background = ContextCompat.getDrawable(context, R.drawable.row_entry_background)

  private val marginVertical = context.resources.getDimension(R.dimen.item_margin)
  private val dividerHeight = context.resources.displayMetrics.density * 0.5
  private val dividerPaint = Paint().apply {
    style = Paint.Style.FILL
    color = ContextCompat.getColor(context, R.color.windowBackground)
  }

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
    val position = parent.getChildAdapterPosition(view)
    val viewType = parent.adapter.getItemViewType(position)

    val isFirstItem = position == 0
    val isLastItem = position == parent.adapter.itemCount - 1
    val shouldAddDivider = !isFirstItem && viewType == EntryAdapter.VIEW_TYPE_DATE

    when {
      isFirstItem -> outRect.top = marginVertical.toInt()
      isLastItem -> outRect.bottom = marginVertical.toInt()
      shouldAddDivider -> outRect.top = dividerHeight.toInt()
    }
  }

  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
    val childCount = parent.childCount
    for (position in 0 until childCount) {
      val child = parent.getChildAt(position)
      val params = child.layoutParams as RecyclerView.LayoutParams

      addDivider(parent, position, child, params, canvas)
      drawRowBackground(position, childCount, child, canvas)
    }
  }

  private fun addDivider(
      parent: RecyclerView,
      position: Int,
      child: View,
      params: RecyclerView.LayoutParams,
      canvas: Canvas
  ) {
    if (position == 0) {
      return
    }

    val viewType = parent.adapter.getItemViewType(position)

    if (viewType == EntryAdapter.VIEW_TYPE_DATE) {
      val dividerLeft = parent.paddingLeft
      val dividerRight = parent.width - parent.paddingRight
      val dividerTop = child.bottom + params.bottomMargin
      val dividerBottom = dividerTop + dividerHeight

      val rect = RectF(dividerLeft.toFloat(), dividerTop.toFloat(), dividerRight.toFloat(), dividerBottom.toFloat())
      canvas.drawRect(rect, dividerPaint)
    }
  }

  private fun drawRowBackground(position: Int, childCount: Int, child: View, canvas: Canvas) {
    val background = when (position) {
      0 -> backgroundFirst
      childCount - 1 -> backgroundLast
      else -> background
    }

    background.bounds = Rect(child.left, child.top, child.right, child.bottom)
    background.draw(canvas)
  }

}