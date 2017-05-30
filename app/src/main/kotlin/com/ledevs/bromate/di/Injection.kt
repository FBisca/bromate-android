package com.ledevs.bromate.di

import android.arch.lifecycle.ViewModel
import javax.inject.Scope



typealias ViewModelBuilder = @JvmSuppressWildcards ViewModelComponentBuilder<@JvmWildcard ViewModelComponent<*>>

interface ViewModelComponent<out T : ViewModel> {
  fun viewModel(): T
}

interface ViewModelComponentBuilder<out C : ViewModelComponent<*>> {
  fun build(): C
}
