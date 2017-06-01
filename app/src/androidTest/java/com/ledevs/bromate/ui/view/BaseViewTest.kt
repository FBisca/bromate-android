package com.ledevs.bromate.ui.view

import android.content.pm.PackageManager
import android.os.IBinder
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice

/**
 * Code based on https://github.com/VictorAlbertos/DeviceAnimationTestRule/blob/master/device-animation-test-rule/src/main/java/io/victoralbertos/device_animation_test_rule/DeviceAnimationTestRule.java
 */
abstract class BaseViewTest {
  private val ANIMATION_PERMISSION = "android.permission.SET_ANIMATION_SCALE"
  private val DISABLED = 0.0f
  private val DEFAULT = 1.0f

  protected fun disableAnimations() {
    val instrumentation = InstrumentationRegistry.getInstrumentation()
    val packageName = InstrumentationRegistry.getInstrumentation().targetContext.packageName


    UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        .executeShellCommand("pm grant $packageName $ANIMATION_PERMISSION")

    val context = instrumentation.context

    val permStatus = context.checkCallingOrSelfPermission(ANIMATION_PERMISSION)
    if (permStatus == PackageManager.PERMISSION_GRANTED) {
      setSystemAnimationsScale(DISABLED)
    }
  }

  protected fun enableAnimations() {
    val instrumentation = InstrumentationRegistry.getInstrumentation()
    val context = instrumentation.context

    val permStatus = context.checkCallingOrSelfPermission(ANIMATION_PERMISSION)
    if (permStatus == PackageManager.PERMISSION_GRANTED) {
      setSystemAnimationsScale(DEFAULT)
    }
  }

  private fun setSystemAnimationsScale(animationScale: Float) {
    val windowManagerStubClazz = Class.forName("android.view.IWindowManager\$Stub")
    val asInterface = windowManagerStubClazz.getDeclaredMethod("asInterface", IBinder::class.java)
    val serviceManagerClazz = Class.forName("android.os.ServiceManager")
    val getService = serviceManagerClazz.getDeclaredMethod("getService", String::class.java)
    val windowManagerClazz = Class.forName("android.view.IWindowManager")
    val setAnimationScales = windowManagerClazz.getDeclaredMethod("setAnimationScales", FloatArray::class.java)
    val getAnimationScales = windowManagerClazz.getDeclaredMethod("getAnimationScales")

    val windowManagerBinder = getService.invoke(null, "window") as IBinder
    val windowManagerObj = asInterface.invoke(null, windowManagerBinder)
    val currentScales = getAnimationScales.invoke(windowManagerObj) as FloatArray
    for (i in currentScales.indices) {
      currentScales[i] = animationScale
    }
    setAnimationScales.invoke(windowManagerObj, currentScales)
  }
}
