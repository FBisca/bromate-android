package com.ledevs.bromate.app.dependencies.mapper

import com.ledevs.bromate.app.dependencies.parser.LocaleFormatter
import com.ledevs.bromate.app.ui.list.model.ResumeListModel
import com.ledevs.bromate.test.ModelFabricator
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Locale

@RunWith(JUnit4::class)
class ResumeListModelMapperTest {

  lateinit var mapper: ResumeListModelMapper

  @Before
  fun setUp() {
    mapper = ResumeListModelMapper(LocaleFormatter(Locale.ENGLISH))
  }

  @Test
  fun test_convert() {
    val list = listOf(
        ModelFabricator.userBalance(),
        ModelFabricator.userBalance(),
        ModelFabricator.userBalance()
    )

    val converted = mapper.convert(list)

    val header = converted.first()

    assertThat(converted.size, `is`(4))
    assertThat(header, instanceOf(ResumeListModel.ResumeHeaderListModel::class.java))
    assertThat(converted[1], instanceOf(ResumeListModel.ResumeUserListModel::class.java))
    assertThat(converted[2], instanceOf(ResumeListModel.ResumeUserListModel::class.java))
    assertThat(converted[3], instanceOf(ResumeListModel.ResumeUserListModel::class.java))
  }
}
