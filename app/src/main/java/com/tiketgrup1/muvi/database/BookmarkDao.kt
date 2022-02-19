package com.tiketgrup1.muvi.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tiketgrup1.muvi.model.Movie

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("SELECT * from movie")
    fun getAllBookmark(): LiveData<List<Movie>>
}