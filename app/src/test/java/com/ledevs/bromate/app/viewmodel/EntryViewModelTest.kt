package com.ledevs.bromate.app.viewmodel

import com.ledevs.bromate.app.dependencies.mapper.EntryListModelMapper
import com.ledevs.bromate.app.dependencies.scheduler.ThreadSchedulers
import com.ledevs.bromate.data.repository.EntryRepository
import com.ledevs.bromate.test.ListModelFabricator.entryDateModel
import com.ledevs.bromate.test.ListModelFabricator.entryRowModel
import com.ledevs.bromate.test.ModelFabricator.entry
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(JUnit4::class)
class EntryViewModelTest {

  lateinit var viewModel: EntryViewModel

  @Mock lateinit var entryRepository: EntryRepository
  @Mock lateinit var entryListModelMapper: EntryListModelMapper
  @Mock lateinit var threadSchedulers: ThreadSchedulers

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)

    viewModel = EntryViewModel(
        entryRepository,
        entryListModelMapper,
        threadSchedulers
    )
  }

  @Test
  fun test_getEntries() {
    val entries = listOf(entry())
    val entriesListModel = listOf(entryDateModel(), entryRowModel())

    `when`(entryRepository.listOpenEntries())
        .thenReturn(Single.just(entries))

    `when`(entryListModelMapper.convert(entries))
        .thenReturn(entriesListModel)

    `when`(threadSchedulers.ioThread()).thenReturn(Schedulers.trampoline())
    `when`(threadSchedulers.mainThread()).thenReturn(Schedulers.trampoline())

    val observer = viewModel.getEntries().test()

    observer.assertComplete()

    val response = observer.values().single()
    assertThat(response, equalTo(entriesListModel))
  }

  @Test
  fun test_ioException() {
    `when`(entryRepository.listOpenEntries())
        .thenReturn(Single.error(IOException()))

    `when`(threadSchedulers.ioThread()).thenReturn(Schedulers.trampoline())
    `when`(threadSchedulers.mainThread()).thenReturn(Schedulers.trampoline())

    val observer = viewModel.getEntries().test()

    observer.assertError(IOException::class.java)
  }
}
