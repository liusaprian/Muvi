package com.tiketgrup1.muvi.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiketgrup1.muvi.data.BookmarkRepository
import com.tiketgrup1.muvi.model.Movie
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application) : ViewModel() {

    private val bookmarkRepository: BookmarkRepository = BookmarkRepository(application)

    fun getAllBookmark() = bookmarkRepository.getAllBookmark()

    fun insertBookmark(movie: Movie) {
        viewModelScope.launch {
            bookmarkRepository.insertBookmark(movie)
        }
    }

    fun deleteBookmark(movie: Movie) {
        viewModelScope.launch {
            bookmarkRepository.deleteBookmark(movie)
        }
    }
}