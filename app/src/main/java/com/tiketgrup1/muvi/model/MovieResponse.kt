package com.tiketgrup1.muvi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
	@field:SerializedName("results")
	val movieList: List<Movie>
) : Parcelable

@Parcelize
data class Movie(
	@field:SerializedName("overview")
	val description: String,

	@field:SerializedName("backdrop_path")
	val backdrop: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val rating: Double,

	@field:SerializedName("id")
	val id: Int,

	var bookmarked: Boolean
) : Parcelable
