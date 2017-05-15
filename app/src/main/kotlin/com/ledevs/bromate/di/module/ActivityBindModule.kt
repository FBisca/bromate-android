package com.ledevs.bromate.di.module

import com.ledevs.bromate.di.InjectionBuilder
import com.ledevs.bromate.di.subcomponent.EntryComponent
import com.ledevs.bromate.app.ui.view.EntryView
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = arrayOf(EntryComponent::class)
)
abstract class ActivityBindModule {
  @Binds
  @IntoMap
  @ClassKey(EntryView::class)
  abstract fun bindsMainActivity(builder: EntryComponent.Builder): InjectionBuilder
}
