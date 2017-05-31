package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.app.ui.view.ResumeView
import com.ledevs.bromate.di.ViewModelBuilder
import com.ledevs.bromate.di.component.EntryComponent
import com.ledevs.bromate.di.component.ResumeComponent
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
  abstract fun bindsEntryViewModel(builder: EntryComponent.Builder): ViewModelBuilder

  @Binds
  @IntoMap
  @ClassKey(ResumeView::class)
  abstract fun bindsResumeViewModel(builder: ResumeComponent.Builder): ViewModelBuilder

}
