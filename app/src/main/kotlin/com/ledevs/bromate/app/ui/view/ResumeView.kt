package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.contract.ResumeContract
import com.ledevs.bromate.app.ui.list.ResumeAdapter
import com.ledevs.bromate.app.ui.list.decorator.ResumeItemDecorator
import com.ledevs.bromate.di.subcomponent.ResumeComponent
import com.ledevs.bromate.extensions.injectBuilder
import javax.inject.Inject

class ResumeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), ResumeContract.View {

  private val resumeList by lazy { findViewById(R.id.list_resume) as RecyclerView }

  @Inject
  lateinit var presenter: ResumeContract.Presenter

  init {
    LayoutInflater.from(context).inflate(R.layout.view_resume, this)
    initInjection()

    val adapter = ResumeAdapter()
    resumeList.addItemDecoration(ResumeItemDecorator(context))
    resumeList.layoutManager = LinearLayoutManager(context)
    resumeList.adapter = adapter
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.attachView(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.detachView()
  }

  private fun initInjection() {
    injectBuilder<ResumeComponent.Builder>()
        .module(ResumeComponent.ResumeModule(this))
        .build()
        .injectMembers(this)
  }
}
