package com.ledevs.bromate.extensions

import android.content.Context
import com.ledevs.bromate.app.App
import com.ledevs.bromate.app.viewmodel.ViewModel

inline fun <T, reified VM : ViewModel> Context.provideViewModel(clazz: Class<T>): VM {
  val app = applicationContext as App

  val component = app.provideBuilder(clazz)

  return component.viewModel() as? VM
      ?: throw IllegalArgumentException("${component.javaClass.simpleName} could not be cast to ${VM::class.java.simpleName}")
}
