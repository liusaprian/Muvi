package com.tiketgrup1.muvi.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.afdhal_fa.imageslider.`interface`.ItemClickListener
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.tiketgrup1.muvi.databinding.FragmentHomeBinding
import com.tiketgrup1.muvi.ui.adapter.MovieAdapter
import com.tiketgrup1.muvi.utils.ResponseConfig
import com.tiketgrup1.muvi.viewmodel.MovieViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularMovieAdapter: MovieAdapter
    private lateinit var upcomingMovieAdapter: MovieAdapter
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popularRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.upcomingRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.requestNowPlayingMovies()
        viewModel.requestPopularMovies()
        viewModel.requestUpcomingMovies()

        viewModel.nowPlayingMovies.observe(this, {
            val slideUIModels = ArrayList<SlideUIModel>()
            it.map { movie ->
                if(movie.backdrop != null)
                    slideUIModels.add(SlideUIModel("${ResponseConfig.IMAGE_BASE_URL}/${ResponseConfig.IMAGE_FILE_SIZE}${movie.backdrop}"))
            }
            binding.nowPlayingSlider.setImageList(slideUIModels)
            binding.nowPlayingSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemClick(model: SlideUIModel, position: Int) {
                    val toDetail = Intent(activity, DetailActivity::class.java)
                    toDetail.putExtra("detail", it[position])
                    startActivity(toDetail)
                }
            })
        })

        viewModel.popularMovies.observe(this, {
            popularMovieAdapter = MovieAdapter(it)
            binding.popularRv.adapter = popularMovieAdapter
        })

        viewModel.upcomingMovies.observe(this, {
            upcomingMovieAdapter = MovieAdapter(it)
            binding.upcomingRv.adapter = upcomingMovieAdapter
        })
    }
}