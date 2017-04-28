package com.ledevs.bromate.app.ui.activities

import android.support.v7.app.AppCompatActivity
import com.ledevs.bromate.app.App
import com.ledevs.bromate.di.ActivityInjectionBuilder

abstract class BaseActivity : AppCompatActivity() {

  @Suppress("UNCHECKED_CAST")
  protected fun <T : ActivityInjectionBuilder> injectBuilder(): T {
    val app = application as App
    return app.viewComponents[javaClass] as T
  }
}