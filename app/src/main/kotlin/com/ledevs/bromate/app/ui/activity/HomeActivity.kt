package com.ledevs.bromate.app.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ledevs.bromate.R
import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.app.ui.view.ResumeView
import io.reactivex.disposables.Disposables

class HomeActivity : BaseActivity(), ViewGroup.OnHierarchyChangeListener {

  companion object {
    const val STATE_NAVIGATION_ITEM_ID = "state_item_id"
  }

  private val appBar by lazy { findViewById(R.id.appBar) as AppBarLayout }
  private val toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }
  private val contentPanel by lazy { findViewById(R.id.contentPanel) as FrameLayout }
  private val bottomNavigation by lazy { findViewById(R.id.bottomNavigation) as BottomNavigationView }

  private val entryView by lazy { EntryView(this) }
  private val resumeView by lazy { ResumeView(this) }

  private val animateFadeInDuration by lazy { resources.getInteger(android.R.integer.config_shortAnimTime).toLong() }
  private val animateFadeOutDuration by lazy { resources.getInteger(android.R.integer.config_shortAnimTime).toLong() }
  private val appBarElevation by lazy { resources.getDimension(R.dimen.toolbar_elevation) }

  private var offsetDisposable = Disposables.empty()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    initActivity()
    initViews(savedInstanceState)
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    outState?.putInt(STATE_NAVIGATION_ITEM_ID, bottomNavigation.selectedItemId)
  }

  override fun onChildViewRemoved(parent: View, child: View) {
    when (child) {
      resumeView -> offsetDisposable.dispose()
    }
  }

  override fun onChildViewAdded(parent: View, child: View) {
    when (child) {
      entryView -> setActionBarElevation(appBarElevation)
      resumeView -> listenToResumeScrolls()
    }
  }

  private fun initActivity() {
    setSupportActionBar(toolbar)

    contentPanel.setOnHierarchyChangeListener(this)
    bottomNavigation.setOnNavigationItemSelectedListener { navigationItemSelected(it.itemId) }
  }

  private fun initViews(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      replaceView(resumeView)
    } else {
      navigationItemSelected(savedInstanceState.getInt(STATE_NAVIGATION_ITEM_ID))
    }
  }

  private fun navigationItemSelected(itemId: Int): Boolean {
    when (itemId) {
      R.id.menu_entries -> replaceView(entryView)
      R.id.menu_home -> replaceView(resumeView)
    }
    return true
  }

  private fun replaceView(newView: View) {
    if (contentPanel.childCount == 0) {
      contentPanel.addView(newView)
    } else {
      val currentView = contentPanel.getChildAt(0)

      ViewCompat.animate(currentView).cancel()
      ViewCompat.animate(newView).cancel()

      animateViewRemoval(currentView) {
        animateViewInclude(newView)
      }
    }
  }

  private inline fun animateViewRemoval(view: View, crossinline afterAction: () -> Unit) {
    ViewCompat.animate(view)
        .alpha(0f)
        .setDuration(animateFadeOutDuration)
        .withEndAction {
          contentPanel.removeView(view)
          afterAction()
        }
        .setInterpolator(LinearOutSlowInInterpolator())
        .start()
  }

  private fun animateViewInclude(view: View) {
    ViewCompat.setAlpha(view, 0f)
    contentPanel.addView(view)

    ViewCompat.animate(view)
        .alpha(1f)
        .setDuration(animateFadeInDuration)
        .withEndAction(null)
        .setInterpolator(FastOutSlowInInterpolator())
        .start()
  }

  private fun setActionBarElevation(elevation: Float) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      appBar.elevation = elevation
    }
  }

  private fun listenToResumeScrolls() {
    offsetDisposable = resumeView.scrollOffsetChanges()
        .subscribe { scrollOffset -> setActionBarElevation(Math.min(appBarElevation, scrollOffset.toFloat())) }
  }
}
