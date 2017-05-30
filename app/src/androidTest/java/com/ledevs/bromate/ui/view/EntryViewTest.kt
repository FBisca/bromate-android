package com.ledevs.bromate.ui.view

import android.support.annotation.IdRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.ledevs.bromate.R
import com.ledevs.bromate.TestApp
import com.ledevs.bromate.ViewRuleTest
import com.ledevs.bromate.app.dependencies.formatter.LocaleFormatter
import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.app.ui.view.EntryView
import com.ledevs.bromate.app.viewmodel.EntryViewModel
import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.EntryType
import com.ledevs.bromate.di.component.EntryComponent
import com.ledevs.bromate.utils.RecyclerViewMatcher
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import java.util.Date
import java.util.Locale

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
  fun test() {
    val list = listOf(entryDateListModel(), entryRowListModel(), entryRowListModel())
    `when`(viewModel.getEntries()).thenReturn(Single.just(list))

    viewRule.attachView()

    onView(listMatcher(R.id.entry_list).atPosition(0)).check(matches(withText("23:03")))
    onView(listMatcher(R.id.entry_list).atPosition(1)).check(matches(hasDescendant(withText("Title"))))
  }

  private fun listMatcher(@IdRes id: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(id)
  }

  private fun entryDateListModel(): EntryListModel {
    return EntryListModel.EntryDateListModel("23:03")
  }

  private fun entryRowListModel(): EntryListModel {
    return EntryListModel.EntryRowListModel(
        LocaleFormatter(Locale.ENGLISH),
        Entry("Title", "Description", 10.0, 5.0, Date(), EntryType.GROCERIES)
    )
  }

}
