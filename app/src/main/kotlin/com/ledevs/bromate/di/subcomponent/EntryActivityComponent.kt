package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.app.contract.EntryContract
import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.di.ActivityComponent
import com.ledevs.bromate.di.ActivityComponentBuilders
import com.ledevs.bromate.di.ActivityModule
import com.ledevs.bromate.di.ViewScope
import com.ledevs.bromate.di.subcomponent.EntryActivityComponent.MainActivityModule
import com.ledevs.bromate.app.presenter.EntryPresenter
import com.ledevs.bromate.app.ui.activities.EntryActivity
import com.ledevs.bromate.data.repository.EntryRepository
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface EntryActivityComponent : ActivityComponent<EntryActivity> {

  @Subcomponent.Builder
  interface Builder : ActivityComponentBuilders<MainActivityModule, EntryActivityComponent>

  @Module
  class MainActivityModule(activity: EntryActivity): ActivityModule<EntryActivity>(activity) {
    @ViewScope
    @Provides
    fun providesPresenter(formatter: Formatter, entryRepository: EntryRepository): EntryContract.Presenter {
      return EntryPresenter(formatter, entryRepository)
    }
  }
}
