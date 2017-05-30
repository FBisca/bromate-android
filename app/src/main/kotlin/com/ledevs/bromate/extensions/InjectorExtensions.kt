package com.ledevs.bromate.extensions

import android.content.Context
import com.ledevs.bromate.app.App

inline fun <T, reified VM> Context.provideViewModel(clazz: Class<T>): VM {
  val app = applicationContext as App
  val component =  app.provideBuilder(clazz)

  return component.viewModel() as? VM
      ?: throw IllegalArgumentException("${component.javaClass.simpleName} could not be cast to ${VM::class.java.simpleName}")
}
