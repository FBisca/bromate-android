package com.ledevs.bromate.app.presenter

import com.ledevs.bromate.app.contract.ResumeContract

class ResumePresenter : ResumeContract.Presenter {

  private lateinit var view: ResumeContract.View

  override fun attachView(view: ResumeContract.View) {
    this.view = view
  }

  override fun detachView() {
  }
}