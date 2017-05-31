package com.ledevs.bromate.data.network

import com.ledevs.bromate.app.dependencies.parser.LocaleFormatter
import com.ledevs.bromate.app.dependencies.parser.MoshiDateAdapter
import com.squareup.moshi.Moshi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.Charset
import java.util.Locale


@RunWith(JUnit4::class)
class EntryApiTest {

  private lateinit var mockWebServer: MockWebServer

  private lateinit var entryApi: EntryApi

  @Before
  fun setUp() {
    mockWebServer = MockWebServer()
    entryApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(MoshiConverterFactory.create(moshi()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(EntryApi::class.java)
  }

  @After
  fun teardown() {
    mockWebServer.shutdown()
  }

  @Test
  fun testEntriesList() {
    enqueueResponse("open-entries-list.json")

    val observer = entryApi.getEntries(mockWebServer.url("/").toString()).test()
    observer.assertComplete()

    val response = observer.values().single()
    assertThat(response.size, `is`(4))

    val firstItem = response.first()
    assertThat(firstItem.title, notNullValue())
    assertThat(firstItem.chargeBackValue, notNullValue())
    assertThat(firstItem.totalValue, notNullValue())
    assertThat(firstItem.date, notNullValue())
    assertThat(firstItem.type, notNullValue())
  }

  @Test
  fun testUserBalanceList() {
    enqueueResponse("user-balance-list.json")

    val observer = entryApi.getUsersBalance(mockWebServer.url("/").toString()).test()
    observer.assertComplete()

    val response = observer.values().single()
    assertThat(response.size, `is`(5))

    val firstItem = response.first()
    assertThat(firstItem.balance, notNullValue())
    assertThat(firstItem.user, notNullValue())
  }

  private fun enqueueResponse(fileName: String) {
    val inputStream = javaClass.classLoader.getResourceAsStream("responses/" + fileName)
    val source = Okio.buffer(Okio.source(inputStream))
    val mockResponse = MockResponse()

    mockWebServer.enqueue(mockResponse
        .setBody(source.readString(Charset.forName("UTF-8"))))
  }

  private fun moshi(): Moshi {
    return Moshi.Builder()
        .add(MoshiDateAdapter(LocaleFormatter(Locale.ENGLISH)))
        .build()
  }
}
