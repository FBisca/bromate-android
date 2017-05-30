package com.ledevs.bromate.utils

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingResource
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.ledevs.bromate.ui.activity.ViewActivity

class ViewRuleTest<in T: View>(
    private val clazz: Class<T>,
    private val attachOnActivityLaunch: Boolean = true
) : ActivityTestRule<ViewActivity>(ViewActivity::class.java) {

  override fun afterActivityLaunched() {
    super.afterActivityLaunched()
    if (attachOnActivityLaunch) {
      attachView()
    }
  }

  @Suppress("UNCHECKED_CAST")
  fun attachView() {
    val constructor = clazz.constructors.first()
    val newInstance = constructor.newInstance(InstrumentationRegistry.getTargetContext()) as T
    bindView(newInstance)
  }

  fun bindView(view: T) {
    val idling = ViewAttachIdlingResource()
    Espresso.registerIdlingResources(idling)

    activity.runOnUiThread {
      activity.bindView(view)

      idling.turnIdle()
      Espresso.unregisterIdlingResources(idling)
    }
  }

  class ViewAttachIdlingResource : IdlingResource {
    var callback: IdlingResource.ResourceCallback? = null
    var idle = false

    override fun getName() = "ViewRuleIdlingResource"
    override fun isIdleNow() = idle
    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
      this.callback = callback
    }

    fun turnIdle() {
      idle = true
      callback?.onTransitionToIdle()
    }
  }

}
