package com.ledevs.bromate.data.model

import android.os.Parcel
import android.os.Parcelable

data class UserBalance(
    val user: User,
    val balance: Double
) : Parcelable {

  constructor(parcel: Parcel)
  : this(
      parcel.readParcelable(User::class.java.classLoader),
      parcel.readDouble()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) {
    dest.writeParcelable(user, flags)
    dest.writeDouble(balance)
  }

  companion object {
    @JvmStatic
    val CREATOR = ParcelableCreatorFactory.creator { UserBalance(it) }
  }
}
