package com.tiketgrup1.muvi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CastResponse(
	@field:SerializedName("cast")
	val casts: List<Cast>,

	@field:SerializedName("id")
	val id: Int,
) : Parcelable

@Parcelize
data class Cast(

	@field:SerializedName("cast_id")
	val castId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String,
) : Parcelable
