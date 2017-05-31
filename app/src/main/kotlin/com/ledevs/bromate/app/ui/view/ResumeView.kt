package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.list.ResumeAdapter
import com.ledevs.bromate.app.ui.list.decorator.ResumeItemDecorator
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.app.ui.list.utils.SimpleDiffCallback
import com.ledevs.bromate.app.viewmodel.ResumeViewModel
import com.ledevs.bromate.extensions.provideViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class ResumeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val resumeList by lazy { findViewById(R.id.list_resume) as RecyclerView }
  private val viewModel: ResumeViewModel

  private var subscription: Disposable = Disposables.empty()

  init {
    LayoutInflater.from(context).inflate(R.layout.view_resume, this)

    viewModel = context.provideViewModel(javaClass)

    val adapter = ResumeAdapter()
    resumeList.addItemDecoration(ResumeItemDecorator(resumeList))
    resumeList.layoutManager = LinearLayoutManager(context)
    resumeList.adapter = adapter
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    subscription = viewModel.getResume().subscribe(
        { showResume(it) },
        { showError() }
    )
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    subscription.dispose()
  }

  fun showResume(list: List<ResumeListModel>) {
    val adapter = resumeList.adapter as ResumeAdapter
    val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(adapter.items, list))
    adapter.items.clear()
    adapter.items.addAll(list)
    diffResult.dispatchUpdatesTo(adapter)
  }

  fun showError() {

  }
}
