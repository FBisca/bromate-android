package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.databinding.ViewMonthSelectorBinding

class MonthSelectorView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val dataBinding: ViewMonthSelectorBinding = ViewMonthSelectorBinding.inflate(LayoutInflater.from(context), this, true)
}
