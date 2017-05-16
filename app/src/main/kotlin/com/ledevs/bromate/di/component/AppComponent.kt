package com.ledevs.bromate.di.component

import com.ledevs.bromate.app.App
import com.ledevs.bromate.di.module.*
import dagger.Component
import dagger.MembersInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidModule::class,
        NetworkModule::class,
        ParserModule::class,
        RepositoryModule::class,
        ViewBindModule::class
    )
)
interface AppComponent : MembersInjector<App>
