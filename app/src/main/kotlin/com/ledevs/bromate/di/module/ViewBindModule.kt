package com.ledevs.bromate.di.module

import com.ledevs.bromate.di.InjectionBuilder
import com.ledevs.bromate.di.subcomponent.EntryComponent
import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.app.ui.view.ResumeView
import com.ledevs.bromate.di.subcomponent.ResumeComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = arrayOf(
        EntryComponent::class,
        ResumeComponent::class
    )
)
abstract class ViewBindModule {
  @Binds
  @IntoMap
  @ClassKey(EntryView::class)
  abstract fun bindsEntryView(builder: EntryComponent.Builder): InjectionBuilder

  @Binds
  @IntoMap
  @ClassKey(ResumeView::class)
  abstract fun bindsResumeView(builder: ResumeComponent.Builder): InjectionBuilder
}
