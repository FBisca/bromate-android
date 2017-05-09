package com.ledevs.bromate.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.Date

data class Entry(
    val title: String,
    val description: String,
    val value: Double,
    val date: Date,
    val type: EntryType
) : Parcelable {

  constructor(parcel: Parcel)
  : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readDouble(),
      Date(parcel.readLong()),
      parcel.readSerializable() as EntryType
  )

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeString(title)
    dest.writeString(description)
    dest.writeDouble(value)
    dest.writeLong(date.time)
    dest.writeSerializable(type)
  }

  override fun describeContents() = 0

  companion object {
    @JvmStatic
    val CREATOR = ParcelableCreatorFactory.creator { Entry(it) }
  }
}

enum class EntryType {
  GROCERIES
}
