package com.ledevs.bromate.ui.activities

import android.os.Bundle
import com.ledevs.bromate.BaseActivity
import com.ledevs.bromate.di.subcomponent.MainActivityComponent

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    builder<MainActivityComponent.Builder>()
        .module(MainActivityComponent.MainActivityModule(this))
        .build()
        .injectMembers(this)
  }
}
