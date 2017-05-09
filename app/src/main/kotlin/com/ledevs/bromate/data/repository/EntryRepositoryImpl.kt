package com.ledevs.bromate.data.repository

import com.ledevs.bromate.app.formatter.Formatter
import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.EntryType
import com.ledevs.bromate.data.network.EntryApi
import io.reactivex.Single
import java.util.*

class EntryRepositoryImpl(
    private val entryApi: EntryApi,
    private val formatter: Formatter
) : EntryRepository {
  override fun listEntries(month: Date): Single<List<Entry>> {
    return Single.just(listOf(
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, Date(), EntryType.GROCERIES),
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, Date(), EntryType.GROCERIES),
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, Date(), EntryType.GROCERIES),
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, Date(), EntryType.GROCERIES),
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, Date(), EntryType.GROCERIES),
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, Date(), EntryType.GROCERIES)
    ))
    //return entryApi.getEntries(formatter.format(month, Formatter.FORMAT_YEAR_MONTH))
  }
}
