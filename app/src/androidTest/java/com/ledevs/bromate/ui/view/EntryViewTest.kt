package com.ledevs.bromate.ui.view

import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.ledevs.bromate.R
import com.ledevs.bromate.TestApp
import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.di.component.EntryComponent
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
class EntryViewTest {

  @Rule
  @JvmField
  var viewRule = ViewRuleTest(EntryView::class.java, attachOnActivityLaunch = false)

  @Mock
  lateinit var component: EntryComponent

  @Mock
  lateinit var viewModel: EntryViewModel

  @Before
  fun setUp() {
    initMocks(this)

    `when`(component.viewModel()).thenReturn(viewModel)
    val testApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    testApp.addStubViewModelComponent(EntryView::class.java, component)
  }

  @After
  fun clear() {
    val testApp = InstrumentationRegistry.getTargetContext().applicationContext as TestApp
    testApp.removeStubs()
  }

  @Test
  fun testListDisplayCorrectly() {
    val dateHeader = ListModelFabricator.entryDateModel()
    val rowEntry = ListModelFabricator.entryRowModel()

    val list = listOf(dateHeader, rowEntry)

    `when`(viewModel.getEntries()).thenReturn(Single.just(list))

    viewRule.attachView()

    onView(listMatcher(R.id.entry_list).atPosition(0))
        .check(matches(withText(dateHeader.date)))

    onView(listMatcher(R.id.entry_list).atPosition(1))
        .check(matches(hasDescendant(withText(rowEntry.title))))
        .check(matches(hasDescendant(withText(rowEntry.description))))
        .check(matches(hasDescendant(withText(rowEntry.chargeBackValue))))
        .check(matches(hasDescendant(withText(rowEntry.totalValue))))
  }

  @Test
  fun testListError() {
    `when`(viewModel.getEntries()).thenReturn(Single.error(IOException()))

    viewRule.attachView()

    onView(withId(R.id.error_view)).check(matches(isDisplayed()))
  }

  private fun listMatcher(@IdRes id: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(id)
  }
}
