package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.contract.MainContract
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import java.util.*

class MainView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), MainContract.View {

  init {
    LayoutInflater.from(context).inflate(R.layout.view_main, this)
  }

  override fun getCurrentMonth(): Date {
    return Date()
  }

  override fun showEntryList(entries: List<EntryViewModel>) {

  }

  override fun showEntryLoadError() {

  }
}
