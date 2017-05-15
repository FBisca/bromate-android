package com.ledevs.bromate.app

import android.app.Application
import com.ledevs.bromate.di.InjectionBuilder
import com.ledevs.bromate.di.component.AppComponent
import com.ledevs.bromate.di.component.DaggerAppComponent
import com.ledevs.bromate.di.module.AndroidModule
import javax.inject.Inject

class App : Application() {

  @Inject
  lateinit var viewComponents: Map<Class<*>, InjectionBuilder>

  lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()
    initApplicationComponent()
  }

  private fun initApplicationComponent() {
    component = DaggerAppComponent.builder()
        .androidModule(AndroidModule(this))
        .build()

    component.injectMembers(this)
  }
}
