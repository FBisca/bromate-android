package com.ledevs.bromate.di

import com.ledevs.bromate.ui.activities.BaseActivity
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class ViewScope

typealias ActivityInjectionBuilder = @JvmSuppressWildcards ActivityComponentBuilders<*, *>

interface ActivityComponent<T : BaseActivity> : MembersInjector<T>

@Module
abstract class ActivityModule<out T: BaseActivity>(val activity: T) {
  @ViewScope
  @Provides
  fun providesActivity(): T {
    return activity
  }
}

interface ActivityComponentBuilders<in M : ActivityModule<*>, C : ActivityComponent<*>> {
  fun module(module: M) : ActivityComponentBuilders<M, C>
  fun build(): C
}
