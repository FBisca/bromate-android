package com.ledevs.bromate.app.ui.view

import android.view.View
import com.ledevs.bromate.app.App
import com.ledevs.bromate.di.InjectionBuilder

interface BaseView {
  @Suppress("UNCHECKED_CAST")
  fun <T : InjectionBuilder> View.injectBuilder(): T {
    val app = context.applicationContext as App
    return app.viewComponents[javaClass] as T
  }
}
