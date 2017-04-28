package com.ledevs.bromate.data.model

import java.util.*

data class Entry(
    val title: String,
    val description: String,
    val value: Double,
    val date: Date,
    val type: EntryType
)

enum class EntryType {
}