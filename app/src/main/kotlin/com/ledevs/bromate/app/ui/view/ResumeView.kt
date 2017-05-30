package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.list.ResumeAdapter
import com.ledevs.bromate.app.ui.list.decorator.ResumeItemDecorator

class ResumeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val resumeList by lazy { findViewById(R.id.list_resume) as RecyclerView }

  init {
    LayoutInflater.from(context).inflate(R.layout.view_resume, this)

    val adapter = ResumeAdapter()
    resumeList.addItemDecoration(ResumeItemDecorator(context))
    resumeList.layoutManager = LinearLayoutManager(context)
    resumeList.adapter = adapter
  }
}
