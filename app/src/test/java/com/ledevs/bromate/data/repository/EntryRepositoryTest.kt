package com.ledevs.bromate.data.repository

import com.ledevs.bromate.data.network.EntryApi
import com.ledevs.bromate.test.ModelFabricator
import io.reactivex.Single
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class EntryRepositoryTest {

  lateinit var repository: EntryRepository
  @Mock lateinit var entryApi: EntryApi

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    repository = EntryRepositoryImpl(entryApi)
  }

  @Test
  fun test_listOpenEntries() {
    val list = listOf(ModelFabricator.entry(), ModelFabricator.entry())

    `when`(entryApi.getEntries(anyString())).thenReturn(Single.just(list))

    val observer = repository.listOpenEntries().test()

    observer.assertComplete()
    val response = observer.values().single()

    assertThat(response, equalTo(list))
  }

  @Test
  fun test_listUserBalance() {
    val list = listOf(ModelFabricator.userBalance(), ModelFabricator.userBalance())

    `when`(entryApi.getUsersBalance(anyString())).thenReturn(Single.just(list))

    val observer = repository.listUsersBalance().test()

    observer.assertComplete()
    val response = observer.values().single()

    assertThat(response, equalTo(list))
  }
}
