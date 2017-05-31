package com.ledevs.bromate.app.viewmodel

import com.ledevs.bromate.app.dependencies.mapper.ResumeListModelMapper
import com.ledevs.bromate.app.dependencies.scheduler.ThreadSchedulers
import com.ledevs.bromate.data.repository.EntryRepository
import com.ledevs.bromate.test.ListModelFabricator.resumeHeaderModel
import com.ledevs.bromate.test.ListModelFabricator.resumeUserModel
import com.ledevs.bromate.test.ModelFabricator.userBalance
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
class ResumeViewModelTest {

  lateinit var viewModel: ResumeViewModel

  @Mock lateinit var entryRepository: EntryRepository
  @Mock lateinit var resumeListModelMapper: ResumeListModelMapper
  @Mock lateinit var threadSchedulers: ThreadSchedulers

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)

    viewModel = ResumeViewModel(
        entryRepository,
        resumeListModelMapper,
        threadSchedulers
    )
  }

  @Test
  fun test_getEntries() {
    val userBalance = listOf(userBalance())
    val listModel = listOf(resumeHeaderModel(), resumeUserModel())

    `when`(entryRepository.listUsersBalance())
        .thenReturn(Single.just(userBalance))

    `when`(resumeListModelMapper.convert(userBalance))
        .thenReturn(listModel)

    `when`(threadSchedulers.ioThread()).thenReturn(Schedulers.trampoline())
    `when`(threadSchedulers.mainThread()).thenReturn(Schedulers.trampoline())

    val observer = viewModel.getResume().test()

    observer.assertComplete()

    val response = observer.values().single()
    assertThat(response, equalTo(listModel))
  }

  @Test
  fun test_ioException() {
    `when`(entryRepository.listUsersBalance())
        .thenReturn(Single.error(IOException()))

    `when`(threadSchedulers.ioThread()).thenReturn(Schedulers.trampoline())
    `when`(threadSchedulers.mainThread()).thenReturn(Schedulers.trampoline())

    val observer = viewModel.getResume().test()

    observer.assertError(IOException::class.java)
  }
}
