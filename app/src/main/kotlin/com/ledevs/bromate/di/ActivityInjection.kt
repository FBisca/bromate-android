package com.ledevs.bromate.di

import com.ledevs.bromate.app.ui.view.BaseView
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class ViewScope

typealias InjectionBuilder = @JvmSuppressWildcards ComponentBuilders<*, *>

interface ViewComponent<T> : MembersInjector<T>

@Module
abstract class ViewModule<out T: BaseView>(val view: T) {
  @ViewScope
  @Provides
  fun providesActivity(): T {
    return view
  }
}

interface ComponentBuilders<in M : ViewModule<*>, C : ViewComponent<*>> {
  fun module(module: M) : ComponentBuilders<M, C>
  fun build(): C
}
