package com.nycschools.app

import android.os.Parcel
import android.os.Parcelable

//SatScore data class
data class SatScore(
    var dbn: String?,
    var schoolName: String?,
    var testTakers: Int,
    var readingAvgScore: Int,
    var mathAvgScore: Int,
    var writingAvgScore: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dbn)
        parcel.writeString(schoolName)
        parcel.writeInt(testTakers)
        parcel.writeInt(readingAvgScore)
        parcel.writeInt(mathAvgScore)
        parcel.writeInt(writingAvgScore)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SatScore> {
        override fun createFromParcel(parcel: Parcel): SatScore {
            return SatScore(parcel)
        }

        override fun newArray(size: Int): Array<SatScore?> {
            return arrayOfNulls(size)
        }
    }
}