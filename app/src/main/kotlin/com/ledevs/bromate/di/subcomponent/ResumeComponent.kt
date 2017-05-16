package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.app.contract.ResumeContract
import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.app.presenter.EntryPresenter
import com.ledevs.bromate.app.presenter.ResumePresenter
import com.ledevs.bromate.app.ui.view.ResumeView
import com.ledevs.bromate.data.repository.EntryRepository
import com.ledevs.bromate.di.ComponentBuilders
import com.ledevs.bromate.di.ViewComponent
import com.ledevs.bromate.di.ViewModule
import com.ledevs.bromate.di.ViewScope
import com.ledevs.bromate.di.subcomponent.EntryComponent.EntryModule
import com.ledevs.bromate.di.subcomponent.ResumeComponent.ResumeModule
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = arrayOf(ResumeModule::class))
interface ResumeComponent : ViewComponent<ResumeView> {

  @Subcomponent.Builder
  interface Builder : ComponentBuilders<ResumeModule, ResumeComponent>

  @Module
  class ResumeModule(view: ResumeView): ViewModule<ResumeView>(view) {
    @ViewScope
    @Provides
    fun providesPresenter(): ResumeContract.Presenter {
      return ResumePresenter()
    }
  }
}
