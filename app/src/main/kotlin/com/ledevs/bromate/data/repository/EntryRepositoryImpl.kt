package com.ledevs.bromate.data.repository

import com.ledevs.bromate.data.model.Entry
import com.ledevs.bromate.data.model.EntryType
import com.ledevs.bromate.data.model.UserBalance
import com.ledevs.bromate.data.network.EntryApi
import io.reactivex.Single
import retrofit2.Retrofit
import java.util.*

class EntryRepositoryImpl(
    private val entryApi: EntryApi
) : EntryRepository {

  override fun listOpenEntries(): Single<List<Entry>> {
    val today = Date()
    val yesterday = Date(today.time - 86400000)
    val twoDaysAgo = Date(yesterday.time - 86400000)
    return Single.just(listOf(
        Entry("Mercado", "Compra de produtos de limpeza", 75.12, 50.08, today, EntryType.GROCERIES),
        Entry("Aluguel", "Aluguel referente a Abril", 3570.0, 2430.0, today, EntryType.GROCERIES),
        Entry("Comgás", "Conta de Gás de Abril", 138.0, 73.0, yesterday, EntryType.GROCERIES),
        Entry("Eletropaulo", "Conta de Energia de Abril", 140.0, 90.0, yesterday, EntryType.GROCERIES),
        Entry("Vivo", "Conta de Internet de Abril", 179.9, 120.0, twoDaysAgo, EntryType.GROCERIES)
    ))
    //return entryApi.getEntries()
  }

  override fun listUsersBalance(): Single<List<UserBalance>> {
  }
}
