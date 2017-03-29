package com.ledevs.bromate.di

import com.ledevs.bromate.ui.activities.BaseActivity
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class ActivityScope

interface ActivityComponent<T : BaseActivity> : MembersInjector<T>

@Module
abstract class ActivityModule<out T: BaseActivity>(val value: T) {
  @ActivityScope
  @Provides
  fun providesValue(): T {
    return value
  }
}

interface ActivityBuilders<in M : ActivityModule<*>, C : ActivityComponent<*>> {
  fun module(module: M) : ActivityBuilders<M, C>
  fun build(): C
}
