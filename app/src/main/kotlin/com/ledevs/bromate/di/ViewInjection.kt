package com.ledevs.bromate.di

import com.ledevs.bromate.contract.BaseView
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class ViewScope

typealias ViewBuilder = @JvmSuppressWildcards ViewBuilders<*, *>

interface ViewComponent<T : BaseView> : MembersInjector<T>

@Module
abstract class ViewModule<out T: BaseView>(val view: T) {
  @ViewScope
  @Provides
  fun providesView(): T {
    return view
  }
}

interface ViewBuilders<in M : ViewModule<*>, C : ViewComponent<*>> {
  fun module(module: M) : ViewBuilders<M, C>
  fun build(): C
}
