package com.ledevs.bromate.di.component

import com.ledevs.bromate.app.viewmodel.ResumeViewModel
import com.ledevs.bromate.di.ViewModelComponent
import com.ledevs.bromate.di.ViewModelComponentBuilder
import com.ledevs.bromate.di.ViewModelScope
import dagger.Subcomponent

@ViewModelScope
@Subcomponent
interface ResumeComponent : ViewModelComponent<ResumeViewModel> {

  @Subcomponent.Builder
  interface Builder : ViewModelComponentBuilder<ResumeComponent>
}
