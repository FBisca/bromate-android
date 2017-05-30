package com.ledevs.bromate.extensions

import android.content.Context
import com.ledevs.bromate.app.App

inline fun <T, reified VM> Context.provideViewModel(clazz: Class<T>): VM {
  val app = applicationContext as App
  val viewModelBuilder = app.viewComponents[clazz]
      ?: throw IllegalArgumentException("${clazz.simpleName} not have builder")

  return viewModelBuilder.build().viewModel() as? VM
      ?: throw IllegalArgumentException("${viewModelBuilder.javaClass.simpleName} could not be cast to ${VM::class.java.simpleName}")
}
