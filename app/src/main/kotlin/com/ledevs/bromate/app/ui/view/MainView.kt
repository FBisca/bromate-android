package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.contract.MainContract

class MainView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), MainContract.View {

  init {
    LayoutInflater.from(context).inflate(R.layout.view_main, this)
  }

  override fun getContentView(): View {
    return this
  }
}
