package com.ledevs.bromate.app.ui.view

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.contract.EntryContract
import com.ledevs.bromate.app.ui.list.EntryAdapter
import com.ledevs.bromate.app.ui.list.decorator.EntryItemDecorator
import com.ledevs.bromate.app.ui.list.utils.SimpleDiffCallback
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.di.subcomponent.EntryComponent
import javax.inject.Inject

class ResumeView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr), BaseView {

  private val entryList by lazy { findViewById(R.id.entry_list) as RecyclerView }

  @Inject
  lateinit var presenter: EntryContract.Presenter

  init {
    LayoutInflater.from(context).inflate(R.layout.view_resume, this)
    initInjection()

    val adapter = EntryAdapter()
    entryList.addItemDecoration(EntryItemDecorator(context))
    entryList.layoutManager = LinearLayoutManager(context)
    entryList.adapter = adapter
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.attachView(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.detachView()
  }

  override fun showEntryList(entries: List<EntryViewModel>) {
    val adapter = entryList.adapter as EntryAdapter
    val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(adapter.items, entries))
    adapter.items.clear()
    adapter.items.addAll(entries)
    diffResult.dispatchUpdatesTo(adapter)
  }

  override fun showEntryLoadError() {
  }

  private fun initInjection() {
    injectBuilder<EntryComponent.Builder>()
        .module(EntryComponent.EntryModule(this))
        .build()
        .injectMembers(this)

  }
}
