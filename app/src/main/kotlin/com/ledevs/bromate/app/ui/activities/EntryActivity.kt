package com.ledevs.bromate.app.ui.activities

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ledevs.bromate.R
import com.ledevs.bromate.app.contract.EntryContract
import com.ledevs.bromate.app.ui.list.EntryAdapter
import com.ledevs.bromate.app.ui.list.decorator.EntryItemDecorator
import com.ledevs.bromate.app.ui.list.utils.SimpleDiffCallback
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.di.subcomponent.EntryActivityComponent
import javax.inject.Inject

class EntryActivity : BaseActivity(), EntryContract.View {

  private val entryList by lazy { findViewById(R.id.entry_list) as RecyclerView }

  @Inject
  lateinit var presenter: EntryContract.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initInjection()
    initActivity()

    presenter.attachView(this)
  }

  private fun initActivity() {
    setContentView(R.layout.view_main)

    val adapter = EntryAdapter()
    entryList.addItemDecoration(EntryItemDecorator(this))
    entryList.layoutManager = LinearLayoutManager(this)
    entryList.adapter = adapter
  }

  override fun onDestroy() {
    super.onDestroy()
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
    injectBuilder<EntryActivityComponent.Builder>()
        .module(EntryActivityComponent.MainActivityModule(this))
        .build()
        .injectMembers(this)

  }
}
