package com.example.tensecondgame

import android.os.Parcel
import android.os.Parcelable

data class Rank(val time: Long) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rank> {
        override fun createFromParcel(parcel: Parcel): Rank {
            return Rank(parcel)
        }

        override fun newArray(size: Int): Array<Rank?> {
            return arrayOfNulls(size)
        }
    }
}