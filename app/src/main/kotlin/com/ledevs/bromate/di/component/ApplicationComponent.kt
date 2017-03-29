package com.ledevs.bromate.di.component

import com.ledevs.bromate.di.module.AndroidModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {

}
