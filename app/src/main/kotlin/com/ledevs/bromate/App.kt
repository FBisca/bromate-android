package com.ledevs.bromate

import android.app.Application
import com.ledevs.bromate.di.component.ApplicationComponent
import com.ledevs.bromate.di.component.DaggerApplicationComponent
import com.ledevs.bromate.di.module.AndroidModule

class App : Application() {

  private lateinit var component: ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    initApplicationComponent()
  }

  private fun initApplicationComponent() {
    component = DaggerApplicationComponent.builder()
        .androidModule(AndroidModule(this))
        .build()
  }
}
