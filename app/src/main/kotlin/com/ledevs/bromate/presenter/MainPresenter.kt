package com.ledevs.bromate.presenter

import com.ledevs.bromate.contract.MainContract

class MainPresenter(
    private val view: MainContract.View
): MainContract.Presenter {

  override fun attachView() {

  }

  override fun detachView() {
  }
}
