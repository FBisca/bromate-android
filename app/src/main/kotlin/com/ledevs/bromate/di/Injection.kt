package com.ledevs.bromate.di

import com.ledevs.bromate.app.viewmodel.ViewModel

// This @Jvm Wildcards annotations are necessary for Dagger2 compatibility
typealias ViewModelBuilder = @JvmSuppressWildcards ViewModelComponentBuilder<@JvmWildcard ViewModelComponent<*>>

interface ViewModelComponent<out T : ViewModel> {
  @ViewModelScope
  fun viewModel(): T
}

interface ViewModelComponentBuilder<out C : ViewModelComponent<*>> {
  fun build(): C
}
