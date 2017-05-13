package com.ledevs.bromate.di.module

import com.ledevs.bromate.di.ActivityInjectionBuilder
import com.ledevs.bromate.di.subcomponent.EntryActivityComponent
import com.ledevs.bromate.app.ui.activities.EntryActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = arrayOf(EntryActivityComponent::class)
)
abstract class ActivityBindModule {
  @Binds
  @IntoMap
  @ClassKey(EntryActivity::class)
  abstract fun bindsMainActivity(builder: EntryActivityComponent.Builder): ActivityInjectionBuilder
}
