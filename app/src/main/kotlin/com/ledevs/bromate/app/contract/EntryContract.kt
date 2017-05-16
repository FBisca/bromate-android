package com.ledevs.bromate.app.contract

import com.ledevs.bromate.app.presenter.BasePresenter
import com.ledevs.bromate.app.ui.view.BaseView
import com.ledevs.bromate.app.viewmodel.EntryViewModel

object EntryContract {
  interface Presenter : BasePresenter<View>
  interface View : BaseView {
    fun showEntryList(entries: List<EntryViewModel>)
    fun showEntryLoadError()
  }
}
