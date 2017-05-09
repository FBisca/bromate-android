package com.ledevs.bromate.app.contract

import com.ledevs.bromate.app.presenter.BasePresenter
import com.ledevs.bromate.app.ui.view.BaseView
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import java.util.Date

object MainContract {
  interface Presenter : BasePresenter<View>
  interface View : BaseView {
    fun getCurrentMonth(): Date
    fun showEntryList(entries: List<EntryViewModel>)
    fun showEntryLoadError()
  }
}
