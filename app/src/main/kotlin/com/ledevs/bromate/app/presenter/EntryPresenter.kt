package com.ledevs.bromate.app.presenter

import com.ledevs.bromate.app.contract.EntryContract
import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.data.repository.EntryRepository
import io.reactivex.disposables.Disposables

class EntryPresenter(
    private val formatter: Formatter,
    private val entryRepository: EntryRepository
): EntryContract.Presenter {

  private lateinit var view: EntryContract.View

  private var subscription = Disposables.empty()

  override fun attachView(view: EntryContract.View) {
    this.view = view
    requestOpenEntries()
  }

  private fun requestOpenEntries() {
    subscription.dispose()

    subscription = entryRepository.listOpenEntries()
        .map { EntryViewModel.createFrom(formatter, it) }
        .subscribe(
            {
              view.showEntryList(it)
            },
            {
              view.showEntryLoadError()
            }
        )
  }

  override fun detachView() {
    subscription.dispose()
  }
}
