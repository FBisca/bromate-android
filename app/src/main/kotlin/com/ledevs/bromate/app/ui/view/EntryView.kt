package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.list.EntryAdapter
import com.ledevs.bromate.app.ui.list.decorator.EntryItemDecorator
import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.app.ui.list.utils.SimpleDiffCallback
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.extensions.provideViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class EntryView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val viewModel: EntryViewModel

  private val entryList by lazy { findViewById(R.id.entry_list) as RecyclerView }
  private val feedbackView by lazy { findViewById(R.id.feedback_view) as FeedbackView }

  private var disposables = CompositeDisposable()

  init {
    LayoutInflater.from(context).inflate(R.layout.view_entry, this)
    viewModel = context.provideViewModel(javaClass)

    val adapter = EntryAdapter()
    entryList.addItemDecoration(EntryItemDecorator(context))
    entryList.layoutManager = LinearLayoutManager(context)
    entryList.adapter = adapter
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    getEntries()
    listenForRetries()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    disposables.clear()
  }

  fun showEntryList(entries: List<EntryListModel>) {
    val adapter = entryList.adapter as EntryAdapter
    val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(adapter.items, entries))
    adapter.items.clear()
    adapter.items.addAll(entries)
    diffResult.dispatchUpdatesTo(adapter)
  }

  fun showLoadingIfNeeded() {
    if (entryList.adapter.itemCount == 0 || feedbackView.isShown) {
      feedbackView.showLoading()
    }
  }

  fun showEntryLoadError() {
    feedbackView.showError(R.string.entry_error_title, R.string.entry_error_message)
  }

  private fun getEntries() {
    viewModel.getEntries()
        .doOnSubscribe { showLoadingIfNeeded() }
        .doOnEvent { _, _ -> feedbackView.hideLoading() }
        .subscribe(
            { showEntryList(it) },
            { showEntryLoadError() }
        )
        .toDisposable()
  }

  private fun listenForRetries() {
    feedbackView.retryEvents()
        .throttleFirst(300, TimeUnit.MILLISECONDS)
        .subscribe { tryAgainClick() }
        .toDisposable()
  }

  private fun tryAgainClick() {
    getEntries()
  }

  private fun Disposable.toDisposable() {
    disposables.add(this)
  }
}
