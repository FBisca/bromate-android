package com.ledevs.bromate.di

import android.arch.lifecycle.ViewModel

typealias ViewModelBuilder = @JvmSuppressWildcards ViewModelComponentBuilder<@JvmWildcard ViewModelComponent<*>>

interface ViewModelComponent<out T : ViewModel> {
  @ViewModelScope
  fun viewModel(): T
}

interface ViewModelComponentBuilder<out C : ViewModelComponent<*>> {
  fun build(): C
}
