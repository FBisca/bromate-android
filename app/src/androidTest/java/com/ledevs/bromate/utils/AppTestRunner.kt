package com.ledevs.bromate.utils

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

open class AppTestRunner : AndroidJUnitRunner() {
  override fun newApplication(
      cl: ClassLoader?,
      className: String?,
      context: Context?
  ): Application {
    return super.newApplication(cl, com.ledevs.bromate.TestApp::class.java.name, context)
  }
}
