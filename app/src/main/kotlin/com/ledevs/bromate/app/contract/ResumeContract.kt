package com.ledevs.bromate.app.contract

import com.ledevs.bromate.app.presenter.BasePresenter
import com.ledevs.bromate.app.ui.view.BaseView

object ResumeContract {
  interface Presenter : BasePresenter<View>
  interface View : BaseView
}