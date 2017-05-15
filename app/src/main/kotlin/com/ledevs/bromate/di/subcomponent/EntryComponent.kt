package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.app.contract.EntryContract
import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.di.ViewComponent
import com.ledevs.bromate.di.ComponentBuilders
import com.ledevs.bromate.di.ViewModule
import com.ledevs.bromate.di.ViewScope
import com.ledevs.bromate.di.subcomponent.EntryComponent.EntryModule
import com.ledevs.bromate.app.presenter.EntryPresenter
import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.data.repository.EntryRepository
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = arrayOf(EntryModule::class))
interface EntryComponent : ViewComponent<EntryView> {

  @Subcomponent.Builder
  interface Builder : ComponentBuilders<EntryModule, EntryComponent>

  @Module
  class EntryModule(view: EntryView): ViewModule<EntryView>(view) {
    @ViewScope
    @Provides
    fun providesPresenter(formatter: Formatter, entryRepository: EntryRepository): EntryContract.Presenter {
      return EntryPresenter(formatter, entryRepository)
    }
  }
}
