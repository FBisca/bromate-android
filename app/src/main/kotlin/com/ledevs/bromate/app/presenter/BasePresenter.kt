package com.ledevs.bromate.app.presenter

interface BasePresenter<in V> {
  fun attachView(view: V)
  fun detachView()
}
