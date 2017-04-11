package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.contract.MainContract
import com.ledevs.bromate.di.ActivityComponent
import com.ledevs.bromate.di.ActivityComponentBuilders
import com.ledevs.bromate.di.ActivityModule
import com.ledevs.bromate.di.ViewScope
import com.ledevs.bromate.di.subcomponent.MainActivityComponent.MainActivityModule
import com.ledevs.bromate.presenter.MainPresenter
import com.ledevs.bromate.ui.activities.MainActivity
import com.ledevs.bromate.ui.view.MainView
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
    fun providesPresenter(view: MainContract.View): MainContract.Presenter {
      return MainPresenter(view)
    }

    @ViewScope
    @Provides
    fun providesView(activity: MainActivity): MainContract.View {
      return MainView(activity)
    }
  }
}
