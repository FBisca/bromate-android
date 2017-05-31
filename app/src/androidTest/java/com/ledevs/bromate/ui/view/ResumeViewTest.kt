package com.ledevs.bromate.ui.view

import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.ledevs.bromate.R
import com.ledevs.bromate.TestApp
import com.ledevs.bromate.app.ui.view.ResumeView
import com.ledevs.bromate.app.viewmodel.ResumeViewModel
import com.ledevs.bromate.di.component.ResumeComponent
import com.ledevs.bromate.test.ListModelFabricator
import com.ledevs.bromate.utils.RecyclerViewMatcher
import com.ledevs.bromate.utils.ViewRuleTest
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ResumeViewTest {

  @Rule
  @JvmField
  var viewRule = ViewRuleTest(ResumeView::class.java, attachOnActivityLaunch = false)

  @Mock
  lateinit var component: ResumeComponent

  @Mock
  lateinit var viewModel: ResumeViewModel

  @Before
  fun setUp() {
    initMocks(this)

    `when`(component.viewModel()).thenReturn(viewModel)
    val testApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    testApp.addStubViewModelComponent(ResumeView::class.java, component)
  }

  @After
  fun clear() {
    val testApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    testApp.removeStubs()
  }

  @Test
  fun testListDisplayCorrectly() {
    val header = ListModelFabricator.resumeHeaderModel()
    val user = ListModelFabricator.resumeUserModel()

    val list = listOf(header, user)

    `when`(viewModel.getResume()).thenReturn(Single.just(list))

    viewRule.attachView()

    onView(listMatcher(R.id.list_resume).atPosition(0))
        .check(matches(hasDescendant(withText(header.valueOwned))))
        .check(matches(hasDescendant(withText(header.valueToReceive))))

    onView(listMatcher(R.id.list_resume).atPosition(1))
        .check(matches(hasDescendant(withText(user.userName))))
        .check(matches(hasDescendant(withText(user.balance))))
  }

  @Test
  fun testListError() {
    `when`(viewModel.getResume()).thenReturn(Single.error(IOException()))

    viewRule.attachView()

    onView(ViewMatchers.withId(R.id.error_view)).check(matches(ViewMatchers.isDisplayed()))
  }

  private fun listMatcher(@IdRes id: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(id)
  }
}
