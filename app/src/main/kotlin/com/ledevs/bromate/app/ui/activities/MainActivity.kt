package com.ledevs.bromate.app.ui.activities

import android.os.Bundle
import com.ledevs.bromate.app.contract.MainContract
import com.ledevs.bromate.di.subcomponent.MainActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var presenter: MainContract.Presenter

  @Inject
  lateinit var view: MainContract.View

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initInjection()

    setContentView(view.getContentView())
    presenter.attachView(view)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }

  private fun initInjection() {
    injectBuilder<MainActivityComponent.Builder>()
        .module(MainActivityComponent.MainActivityModule(this))
        .build()
        .injectMembers(this)
  }
}
