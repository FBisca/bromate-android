package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.app.contract.MainContract
import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.di.ActivityComponent
import com.ledevs.bromate.di.ActivityComponentBuilders
import com.ledevs.bromate.di.ActivityModule
import com.ledevs.bromate.di.ViewScope
import com.ledevs.bromate.di.subcomponent.MainActivityComponent.MainActivityModule
import com.ledevs.bromate.app.presenter.MainPresenter
import com.ledevs.bromate.app.ui.activities.MainActivity
import com.ledevs.bromate.app.ui.view.MainView
import com.ledevs.bromate.data.repository.EntryRepository
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent : ActivityComponent<MainActivity> {

  @Subcomponent.Builder
  interface Builder : ActivityComponentBuilders<MainActivityModule, MainActivityComponent>

  @Module
  class MainActivityModule(activity: MainActivity): ActivityModule<MainActivity>(activity) {
    @ViewScope
    @Provides
    fun providesPresenter(formatter: Formatter, entryRepository: EntryRepository): MainContract.Presenter {
      return MainPresenter(formatter, entryRepository)
    }

    @ViewScope
    @Provides
    fun providesView(activity: MainActivity): MainContract.View {
      return MainView(activity)
    }
  }
}
