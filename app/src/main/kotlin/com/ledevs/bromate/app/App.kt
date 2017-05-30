package com.ledevs.bromate.app

import android.app.Application
import com.ledevs.bromate.di.ViewModelBuilder
import com.ledevs.bromate.di.component.AppComponent
import com.ledevs.bromate.di.component.DaggerAppComponent
import com.ledevs.bromate.di.module.AppModule
import javax.inject.Inject

class App : Application() {

  @Inject
  lateinit var viewComponents: Map<Class<*>, ViewModelBuilder>

  lateinit var component: AppComponent

  override fun onCreate() {
    super.onCreate()
    initApplicationComponent()
  }

  private fun initApplicationComponent() {
    component = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
    component.injectMembers(this)
  }
}
