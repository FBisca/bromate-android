package com.ledevs.bromate

import android.support.v7.app.AppCompatActivity
import com.ledevs.bromate.di.module.ActivityBuilder

abstract class BaseActivity : AppCompatActivity() {

  @Suppress("UNCHECKED_CAST")
  protected fun <T : ActivityBuilder> builder(): T {
    val app = application as App
    return app.activityComponents[javaClass] as T
  }
}
