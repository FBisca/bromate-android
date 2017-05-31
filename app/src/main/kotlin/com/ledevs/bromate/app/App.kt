package com.ledevs.bromate.app

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.ledevs.bromate.di.ViewModelBuilder
import com.ledevs.bromate.di.ViewModelComponent
import com.ledevs.bromate.di.component.AppComponent
import com.ledevs.bromate.di.component.DaggerAppComponent
import com.ledevs.bromate.di.module.AppModule
import javax.inject.Inject

@VisibleForTesting
class App : Application() {
  @Inject
  lateinit var viewModelBuilders: Map<Class<*>, ViewModelBuilder>

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

  fun provideBuilder(clazz: Class<*>): ViewModelComponent<*> {
    val component =  viewModelBuilders[clazz]
        ?: throw IllegalArgumentException("${clazz.simpleName} doesn't have builder")
    return component.build()
  }
}
