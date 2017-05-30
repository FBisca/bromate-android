package com.ledevs.bromate.di.module

import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.di.ViewModelComponent
import com.ledevs.bromate.di.ViewModelComponentBuilder
import com.ledevs.bromate.di.component.EntryComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(
    subcomponents = arrayOf(
        EntryComponent::class
    )
)
abstract class ViewBindModule {
  @Binds
  @IntoMap
  @ClassKey(EntryView::class)
  abstract fun bindsEntryView(builder: EntryComponent.Builder): @JvmSuppressWildcards ViewModelComponentBuilder<@JvmWildcard ViewModelComponent<*>>

}
