package com.ledevs.bromate.app.ui.activities

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ledevs.bromate.R
import com.ledevs.bromate.app.contract.MainContract
import com.ledevs.bromate.app.ui.adapters.EntryAdapter
import com.ledevs.bromate.app.ui.adapters.utils.SimpleDiffCallback
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.di.subcomponent.MainActivityComponent
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

  private val entryList by lazy { findViewById(R.id.entry_list) as RecyclerView }

  @Inject
  lateinit var presenter: MainContract.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initInjection()
    initActivity()

    presenter.attachView(this)
  }

  private fun initActivity() {
    setContentView(R.layout.view_main)

    val adapter = EntryAdapter()
    entryList.layoutManager = LinearLayoutManager(this)
    entryList.adapter = adapter
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }

  override fun getCurrentMonth(): Date {
    return Date()
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
    injectBuilder<MainActivityComponent.Builder>()
        .module(MainActivityComponent.MainActivityModule(this))
        .build()
        .injectMembers(this)
  }
}
