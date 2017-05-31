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
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class EntryView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

  private val viewModel: EntryViewModel

  private val entryList by lazy { findViewById(R.id.entry_list) as RecyclerView }
  private var subscription: Disposable = Disposables.empty()

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
  }

  private fun getEntries() {
    subscription = viewModel.getEntries().subscribe(
        { showEntryList(it) },
        { showEntryLoadError() }
    )
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    subscription.dispose()
  }

  fun showEntryList(entries: List<EntryListModel>) {
    val adapter = entryList.adapter as EntryAdapter
    val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(adapter.items, entries))
    adapter.items.clear()
    adapter.items.addAll(entries)
    diffResult.dispatchUpdatesTo(adapter)
  }

  fun showEntryLoadError() {

  }
}
