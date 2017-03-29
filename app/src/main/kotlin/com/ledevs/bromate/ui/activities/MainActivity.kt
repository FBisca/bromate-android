package com.ledevs.bromate.ui.activities

import android.os.Bundle
import com.ledevs.bromate.ui.activities.BaseActivity
import com.ledevs.bromate.di.subcomponent.MainActivityComponent

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    injectBuilder<MainActivityComponent.Builder>()
        .module(MainActivityComponent.MainActivityModule(this))
        .build()
        .injectMembers(this)
  }
}
