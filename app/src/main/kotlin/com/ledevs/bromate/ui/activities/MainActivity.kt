package com.ledevs.bromate.ui.activities

import android.os.Bundle
import com.ledevs.bromate.contract.MainContract
import com.ledevs.bromate.di.subcomponent.MainActivityComponent

class MainActivity : BaseActivity(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    injectBuilder<MainActivityComponent.Builder>()
        .module(MainActivityComponent.MainViewModule(this))
        .build()
        .injectMembers(this)
  }
}
