package com.ledevs.bromate.app.viewmodel

sealed class ResumeViewModel {

  class ResumeHeaderViewModel : ResumeViewModel()
  class ResumeUserViewModel : ResumeViewModel()

}