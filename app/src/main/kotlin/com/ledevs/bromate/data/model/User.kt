package com.ledevs.bromate.data.model

import android.os.Parcel
import android.os.Parcelable

data class User(
    val name: String,
    val email: String,
    val imageUrl: String
) : Parcelable {

  constructor(parcel: Parcel)
  : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeString(name)
    dest.writeString(email)
    dest.writeString(imageUrl)
  }

  companion object {
    @JvmStatic
    val CREATOR = ParcelableCreatorFactory.creator { User(it) }
  }
}
