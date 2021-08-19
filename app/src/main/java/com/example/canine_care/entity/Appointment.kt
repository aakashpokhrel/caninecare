package com.example.canine_care.entity

import android.os.Parcel
import android.os.Parcelable

class Appointment (
    val _id: String? = null,
    val fullname: String? = null,
    val mnumber: String? = null,
    val email: String? = null,
    val apdate: String? = null,
    val apaddress: String? = null,
    val photo: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(fullname)
        parcel.writeString(mnumber)
        parcel.writeString(email)
        parcel.writeString(apdate)
        parcel.writeString(apaddress)
        parcel.writeString(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Appointment> {
        override fun createFromParcel(parcel: Parcel): Appointment {
            return Appointment(parcel)
        }

        override fun newArray(size: Int): Array<Appointment?> {
            return arrayOfNulls(size)
        }
    }
}
