package com.ledevs.bromate

import com.ledevs.bromate.app.App
import com.ledevs.bromate.di.ViewModelComponent

class TestApp : App() {

  private val stubComponents = mutableMapOf<Class<*>, ViewModelComponent<*>>()

  fun addStubViewModelComponent(clazz: Class<*>, component: ViewModelComponent<*>) {
    stubComponents[clazz] = component
  }

  fun removeStubs() {
    stubComponents.clear()
  }

  override fun provideBuilder(clazz: Class<*>): ViewModelComponent<*> {
    return stubComponents[clazz] ?: super.provideBuilder(clazz)
  }
}
