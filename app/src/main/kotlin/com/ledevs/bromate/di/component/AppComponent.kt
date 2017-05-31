package com.ledevs.bromate.di.component

import com.ledevs.bromate.app.App
import com.ledevs.bromate.di.module.AppModule
import com.ledevs.bromate.di.module.NetworkModule
import com.ledevs.bromate.di.module.ParserModule
import com.ledevs.bromate.di.module.RepositoryModule
import com.ledevs.bromate.di.module.SchedulerModule
import com.ledevs.bromate.di.module.ViewBindModule
import dagger.Component
import dagger.MembersInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ParserModule::class,
        RepositoryModule::class,
        SchedulerModule::class,
        ViewBindModule::class
    )
)
interface AppComponent : MembersInjector<App>
