package com.ledevs.bromate.di

import com.ledevs.bromate.BaseActivity
import dagger.MembersInjector
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class ActivityScope

interface ActivitySubcomponent<T : BaseActivity> : MembersInjector<T>

@Module
abstract class ActivityModule<out T: BaseActivity>(val value: T) {
  @ActivityScope
  @Provides
  fun providesValue(): T {
    return value
  }
}

interface ActivityBuilders<in M : ActivityModule<*>, C : ActivitySubcomponent<*>> {
  fun module(module: M) : ActivityBuilders<M, C>
  fun build(): C
}
