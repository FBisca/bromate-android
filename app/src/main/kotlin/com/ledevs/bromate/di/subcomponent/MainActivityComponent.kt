package com.ledevs.bromate.di.subcomponent

import com.ledevs.bromate.di.ActivityBuilders
import com.ledevs.bromate.di.ActivityModule
import com.ledevs.bromate.di.ActivityScope
import com.ledevs.bromate.di.ActivitySubcomponent
import com.ledevs.bromate.di.subcomponent.MainActivityComponent.MainActivityModule
import com.ledevs.bromate.ui.activities.MainActivity
import dagger.Module
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent : ActivitySubcomponent<MainActivity> {

  @Subcomponent.Builder
  interface Builder : ActivityBuilders<MainActivityModule, MainActivityComponent>

  @Module
  class MainActivityModule(activity: MainActivity): ActivityModule<MainActivity>(activity)
}
