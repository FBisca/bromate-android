package com.ledevs.bromate.di.module

import com.ledevs.bromate.di.ActivityBuilders
import com.ledevs.bromate.di.subcomponent.MainActivityComponent
import com.ledevs.bromate.ui.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = arrayOf(MainActivityComponent::class)
)
abstract class ActivitiesModule {
  @Binds
  @IntoMap
  @ClassKey(MainActivity::class)
  abstract fun bindsMainActivity(builder: MainActivityComponent.Builder): ActivityBuilder
}

typealias ActivityBuilder = @JvmSuppressWildcards ActivityBuilders<*, *>
