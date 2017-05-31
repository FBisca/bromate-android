package com.ledevs.bromate.app.dependencies.parser

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date
import javax.inject.Inject

class MoshiDateAdapter @Inject constructor(
    private val stringFormatter: StringFormatter
) {

  @FromJson fun fromJson(value: String) = stringFormatter.parse(value, StringFormatter.FORMAT_TIMESTAMP)

  @ToJson fun toJson(value: Date?) = when (value) {
    null -> "null"
    else -> stringFormatter.format(value, StringFormatter.FORMAT_TIMESTAMP)
  }

}
