package com.ledevs.bromate.di.component

import com.ledevs.bromate.App
import com.ledevs.bromate.di.module.ActivitiesModule
import com.ledevs.bromate.di.module.AndroidModule
import dagger.Component
import dagger.MembersInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidModule::class,
        ActivitiesModule::class
    )
)
interface AppComponent : MembersInjector<App>
