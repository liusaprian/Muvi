package com.tiketgrup1.muvi.ui

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tiketgrup1.muvi.R
import com.tiketgrup1.muvi.databinding.ActivityDetailBinding
import com.tiketgrup1.muvi.model.Movie
import com.tiketgrup1.muvi.ui.adapter.CastAdapter
import com.tiketgrup1.muvi.utils.ResponseConfig
import com.tiketgrup1.muvi.viewmodel.BookmarkViewModel
import com.tiketgrup1.muvi.viewmodel.MovieViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var data: Movie
    private lateinit var bookmarkViewModel: BookmarkViewModel
    private val movieViewModel by viewModels<MovieViewModel>()
    private lateinit var castAdapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Movie>("detail")?.let {
            data = it
        }

        movieViewModel.getMovieCasts(data.id)

        bookmarkViewModel = BookmarkViewModel(this.applicationContext as Application)
        bookmarkViewModel.getAllBookmark().observe(this, {
            data.bookmarked = it.any { movie -> movie.id == data.id }
            setBookmarkState(data.bookmarked)
        })

        binding.backBtn.setOnClickListener {
            finish()
        }

        Glide.with(this)
            .load("${ResponseConfig.IMAGE_BASE_URL}/${ResponseConfig.IMAGE_FILE_SIZE}${data.backdrop}")
            .into(binding.detailBackdrop)

        Glide.with(this)
            .load("${ResponseConfig.IMAGE_BASE_URL}/${ResponseConfig.IMAGE_FILE_SIZE}${data.posterPath}")
            .into(binding.detailPoster)

        with(binding) {
            detailTitle.text = data.title
            detailDate.text = data.releaseDate.subSequence(0, 4)
            detailOverview.text = data.description
        }

        binding.bookmarkBtn.setOnClickListener {
            data.bookmarked = !data.bookmarked
            if(data.bookmarked) bookmarkViewModel.insertBookmark(data)
            else bookmarkViewModel.deleteBookmark(data)
            setBookmarkState(data.bookmarked)
        }

        binding.castsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        movieViewModel.casts.observe(this, {
            castAdapter = CastAdapter(it)
            binding.castsRv.adapter = castAdapter
        })
    }

    private fun setBookmarkState(bookmark: Boolean) {
        binding.bookmarkBtn.setImageResource(
            if(bookmark) R.drawable.ic_baseline_bookmark_24
            else R.drawable.ic_baseline_bookmark_border_24
        )
    }
}