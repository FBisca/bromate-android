package com.ledevs.bromate.app.ui.activities

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.view.EntryView

class HomeActivity : BaseActivity() {

  private val appBar by lazy { findViewById(R.id.appBar) as AppBarLayout }
  private val toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
  private val contentPanel by lazy { findViewById(R.id.contentPanel) as FrameLayout }
  private val bottomNavigation by lazy { findViewById(R.id.bottomNavigation) as BottomNavigationView }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    initActivity()
  }

  fun initActivity() {
    setSupportActionBar(toolbar)

    contentPanel.addView(EntryView(this))
  }
}