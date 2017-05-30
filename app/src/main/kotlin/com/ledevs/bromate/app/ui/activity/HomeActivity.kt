package com.ledevs.bromate.app.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.app.ui.view.ResumeView

class HomeActivity : BaseActivity() {

  private val toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
  private val contentPanel by lazy { findViewById(R.id.contentPanel) as FrameLayout }
  private val bottomNavigation by lazy { findViewById(R.id.bottomNavigation) as BottomNavigationView }

  private val entryView by lazy { EntryView(this) }
  private val resumeView by lazy { ResumeView(this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    initActivity()
    initViews(savedInstanceState)
  }

  private fun initActivity() {
    setSupportActionBar(toolbar)

    bottomNavigation.setOnNavigationItemSelectedListener { navigationItemSelected(it.itemId) }
  }

  private fun initViews(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      replaceView(resumeView)
    } else {
      navigationItemSelected(bottomNavigation.selectedItemId)
    }
  }

  private fun replaceView(newView: View) {
    if (contentPanel.childCount == 0) {
      contentPanel.addView(newView)
    } else {
      val currentView = contentPanel.getChildAt(0)

      animateViewRemoval(currentView) {
        animateViewInclude(newView)
      }
    }
  }

  private fun navigationItemSelected(itemId: Int): Boolean {
    when (itemId) {
      R.id.menu_entries -> replaceView(entryView)
      R.id.menu_home -> replaceView(resumeView)
    }

    return true
  }

  private inline fun animateViewRemoval(view: View, crossinline afterAction: () -> Unit) {
    ViewCompat.animate(view)
        .alpha(0f)
        .setListener(object : ViewPropertyAnimatorListenerAdapter() {
          override fun onAnimationEnd(view: View?) {
            contentPanel.removeView(view)
            afterAction()
          }
        })
        .setInterpolator(LinearOutSlowInInterpolator())
        .start()
  }

  private fun animateViewInclude(view: View) {
    ViewCompat.setAlpha(view, 0f)
    contentPanel.addView(view)

    ViewCompat.animate(view)
        .alpha(1f)
        .setListener(ViewPropertyAnimatorListenerAdapter())
        .setInterpolator(FastOutSlowInInterpolator())
        .start()
  }
}
