package com.ledevs.bromate.app.dependencies.mapper

import com.ledevs.bromate.app.dependencies.parser.LocaleFormatter
import com.ledevs.bromate.app.ui.list.model.EntryListModel
import com.ledevs.bromate.test.ModelFabricator
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Locale

@RunWith(JUnit4::class)
class EntryListModelMapperTest {

  lateinit var mapper: EntryListModelMapper

  @Before
  fun setUp() {
    mapper = EntryListModelMapper(LocaleFormatter(Locale.ENGLISH))
  }

  @Test
  fun test_convert() {
    val listEntries = listOf(
        ModelFabricator.entry(),
        ModelFabricator.entry(),
        ModelFabricator.entry()
    )

    val converted = mapper.convert(listEntries)

    val header = converted.first()
    val item = converted[1]

    assertThat(converted.size, `is`(4))
    assertThat(header, instanceOf(EntryListModel.EntryDateListModel::class.java))
    assertThat(item, instanceOf(EntryListModel.EntryRowListModel::class.java))

    val rowListModel = item as EntryListModel.EntryRowListModel

    assertThat(rowListModel.title, equalTo(listEntries.first().title))
    assertThat(rowListModel.description, equalTo(listEntries.first().description))
  }
}
