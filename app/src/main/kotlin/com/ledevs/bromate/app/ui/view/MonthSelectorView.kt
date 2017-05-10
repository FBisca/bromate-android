package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.R
import java.util.Calendar

class MonthSelectorView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val monthTabLayout by lazy { findViewById(R.id.month_tablayout) as TabLayout }

  init {
    LayoutInflater.from(context).inflate(R.layout.view_month_selector, this)

    val months = allMonths()

    months.forEach {
      monthTabLayout.addTab(
          monthTabLayout.newTab()
              .setText(it)
      )
    }
  }

  private fun allMonths(): List<String> {
    val calendar = Calendar.getInstance()
    calendar.clear()

    val months = arrayOf(
        Calendar.JANUARY,
        Calendar.FEBRUARY,
        Calendar.MARCH,
        Calendar.APRIL,
        Calendar.MAY,
        Calendar.JUNE,
        Calendar.JULY,
        Calendar.AUGUST,
        Calendar.SEPTEMBER,
        Calendar.OCTOBER,
        Calendar.NOVEMBER,
        Calendar.DECEMBER
    )

    return months.map {
      calendar.set(Calendar.MONTH, it)
      calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, resources.configuration.locale)
    }
  }
}
