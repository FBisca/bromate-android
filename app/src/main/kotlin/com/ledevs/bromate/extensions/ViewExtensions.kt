package com.ledevs.bromate.extensions

import android.view.LayoutInflater
import android.view.View
import com.ledevs.bromate.app.App
import com.ledevs.bromate.di.InjectionBuilder

fun View.layoutInflater(): LayoutInflater {
  return LayoutInflater.from(context)
}

@Suppress("UNCHECKED_CAST")
fun <T : InjectionBuilder> View.injectBuilder(): T {
  val app = context.applicationContext as App
  return app.viewComponents[javaClass] as T
}