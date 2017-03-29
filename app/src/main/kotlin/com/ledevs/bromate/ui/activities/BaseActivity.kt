package com.ledevs.bromate.ui.activities

import android.support.v7.app.AppCompatActivity
import com.ledevs.bromate.App
import com.ledevs.bromate.di.ViewBuilder

abstract class BaseActivity : AppCompatActivity() {

  @Suppress("UNCHECKED_CAST")
  protected fun <T : ViewBuilder> injectBuilder(): T {
    val app = application as App
    return app.viewComponents[javaClass] as T
  }
}
