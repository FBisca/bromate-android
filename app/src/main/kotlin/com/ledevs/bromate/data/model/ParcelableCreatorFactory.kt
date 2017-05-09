package com.ledevs.bromate.data.model

import android.os.Parcel
import android.os.Parcelable

object ParcelableCreatorFactory {
  inline fun <reified T> creator(
      crossinline fromParcel: (Parcel) -> T
  ) = object : Parcelable.Creator<T> {
    override fun createFromParcel(source: Parcel) = fromParcel(source)
    override fun newArray(size: Int) = arrayOfNulls<T>(size)
  }
}
