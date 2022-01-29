package com.tiketgrup1.muvi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.tiketgrup1.muvi.data.MovieRepository
import com.tiketgrup1.muvi.model.Movie
import com.tiketgrup1.muvi.utils.ResponseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {
    private val repo: MovieRepository = MovieRepository()

    private val _popularMovies = MutableLiveData<ArrayList<Movie>>()
    val popularMovies: LiveData<ArrayList<Movie>> = _popularMovies

    private val _nowPlayingMovies = MutableLiveData<ArrayList<SlideUIModel>>()
    val nowPlayingMovies: LiveData<ArrayList<SlideUIModel>> = _nowPlayingMovies

    private val _upcomingMovies = MutableLiveData<ArrayList<Movie>>()
    val upcomingMovies: LiveData<ArrayList<Movie>> = _upcomingMovies

    fun requestPopularMovies() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                val dataNeeded = ArrayList<Movie>()
                dataNeeded.addAll(repo.getMovies(ResponseConfig.POPULAR).movieList)
                dataNeeded
            }
            _popularMovies.postValue(result)
        }
    }

    fun requestNowPlayingMovies() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                val dataNeeded = ArrayList<SlideUIModel>()
                repo.getMovies(ResponseConfig.NOW_PLAYING).movieList.map { movie ->
                    if(movie.backdrop != null)
                        dataNeeded.add(SlideUIModel("${ResponseConfig.IMAGE_BASE_URL}/${ResponseConfig.IMAGE_FILE_SIZE}${movie.backdrop}"))
                }
                dataNeeded
            }
            _nowPlayingMovies.postValue(result)
        }
    }

    fun requestUpcomingMovies() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                val dataNeeded = ArrayList<Movie>()
                dataNeeded.addAll(repo.getMovies(ResponseConfig.UPCOMING).movieList)
                dataNeeded
            }
            _upcomingMovies.postValue(result)
        }
    }
}