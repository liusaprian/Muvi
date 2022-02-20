package com.tiketgrup1.muvi.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
	@field:SerializedName("results")
	val movieList: List<Movie>
) : Parcelable

@Entity
@Parcelize
data class Movie(
	@ColumnInfo(name = "desc")
	@field:SerializedName("overview")
	val description: String,

	@ColumnInfo(name = "backdrop")
	@field:SerializedName("backdrop_path")
	val backdrop: String? = null,

	@ColumnInfo(name = "title")
	@field:SerializedName("title")
	val title: String,

	@ColumnInfo(name = "posterPath")
	@field:SerializedName("poster_path")
	val posterPath: String,

	@ColumnInfo(name = "releaseDate")
	@field:SerializedName("release_date")
	val releaseDate: String,

	@ColumnInfo(name = "rating")
	@field:SerializedName("vote_average")
	val rating: Double,

	@PrimaryKey
	@ColumnInfo(name = "id")
	@field:SerializedName("id")
	val id: Int,

	var bookmarked: Boolean
) : Parcelable
