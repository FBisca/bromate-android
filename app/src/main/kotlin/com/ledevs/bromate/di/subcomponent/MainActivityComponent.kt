package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.contract.MainContract
import com.ledevs.bromate.di.ViewBuilders
import com.ledevs.bromate.di.ViewComponent
import com.ledevs.bromate.di.ViewModule
import com.ledevs.bromate.di.ViewScope
import com.ledevs.bromate.di.subcomponent.MainActivityComponent.MainViewModule
import dagger.Module
import dagger.Subcomponent

@ViewScope
@Subcomponent(modules = arrayOf(MainViewModule::class))
interface MainActivityComponent : ViewComponent<MainContract.View> {

  @Subcomponent.Builder
  interface Builder : ViewBuilders<MainViewModule, MainActivityComponent>

  @Module
  class MainViewModule(view: MainContract.View): ViewModule<MainContract.View>(view)
}
