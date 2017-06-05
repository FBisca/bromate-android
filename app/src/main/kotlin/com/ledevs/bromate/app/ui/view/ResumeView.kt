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
import com.ledevs.bromate.app.ui.list.utils.RecyclerViewListener
import com.ledevs.bromate.app.ui.list.utils.SimpleDiffCallback
import com.ledevs.bromate.app.viewmodel.ResumeViewModel
import com.ledevs.bromate.extensions.provideViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class ResumeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val resumeList by lazy { findViewById(R.id.list_resume) as RecyclerView }
  private val feedbackView by lazy { findViewById(R.id.feedback_view) as FeedbackView }
  private val viewModel: ResumeViewModel
  private val recyclerViewListener: RecyclerViewListener
  private val itemDecorator: ResumeItemDecorator

  private var disposables = CompositeDisposable()

  init {
    LayoutInflater.from(context).inflate(R.layout.view_resume, this)

    viewModel = context.provideViewModel(javaClass)
    itemDecorator = ResumeItemDecorator(context)

    val adapter = ResumeAdapter()

    resumeList.addItemDecoration(itemDecorator)
    resumeList.layoutManager = LinearLayoutManager(context)
    resumeList.adapter = adapter

    recyclerViewListener = RecyclerViewListener(resumeList)

    listenForScroll()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    getResume()
    listenForRetries()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    disposables.clear()
  }

  fun showResume(list: List<ResumeListModel>) {
    val adapter = resumeList.adapter as ResumeAdapter
    val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(adapter.items, list))
    adapter.items.clear()
    adapter.items.addAll(list)
    diffResult.dispatchUpdatesTo(adapter)
  }

  fun showError() {
    feedbackView.showError(R.string.resume_error_title, R.string.resume_error_message)
  }

  fun scrollOffsetChanges(): Flowable<Int> {
    return recyclerViewListener.offsetScrollsChanges()
  }

  private fun showLoadingIfNeeded() {
    if (resumeList.adapter.itemCount == 0) {
      feedbackView.showLoading()
    }
  }

  private fun listenForRetries() {
    feedbackView.retryEvents()
        .throttleFirst(300, TimeUnit.MILLISECONDS)
        .subscribe { getResume() }
        .toDisposable()
  }

  private fun listenForScroll() {
    recyclerViewListener.offsetScrollsChanges()
        .subscribe { itemDecorator.setParentScrollOffset(it) }
  }

  private fun getResume() {
    viewModel.getResume()
        .doOnSubscribe { showLoadingIfNeeded() }
        .doOnEvent { _, _ -> feedbackView.hideLoading() }
        .subscribe(
            { showResume(it) },
            { showError() }
        )
        .toDisposable()
  }

  private fun Disposable.toDisposable() {
    disposables.add(this)
  }

}
