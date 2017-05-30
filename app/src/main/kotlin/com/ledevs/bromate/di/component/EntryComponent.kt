package com.ledevs.bromate.di.component

import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.di.ViewModelComponent
import com.ledevs.bromate.di.ViewModelComponentBuilder
import com.ledevs.bromate.di.ViewModelScope
import dagger.Subcomponent

@ViewModelScope
@Subcomponent
interface EntryComponent : ViewModelComponent<EntryViewModel> {

  @Subcomponent.Builder
  interface Builder : ViewModelComponentBuilder<EntryComponent>
}
