package com.ledevs.bromate.app.presenter

import com.ledevs.bromate.app.contract.MainContract
import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.data.repository.EntryRepository
import io.reactivex.disposables.Disposables

class MainPresenter(
    private val formatter: Formatter,
    private val entryRepository: EntryRepository
): MainContract.Presenter {

  private var subscription = Disposables.empty()

  override fun attachView(view: MainContract.View) {
    subscription.dispose()

    subscription = entryRepository.listEntries(view.getCurrentMonth())
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
