package com.tiketgrup1.muvi.ui

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.tiketgrup1.muvi.databinding.FragmentBookmarkBinding
import com.tiketgrup1.muvi.model.Movie
import com.tiketgrup1.muvi.ui.adapter.BookmarkAdapter
import com.tiketgrup1.muvi.viewmodel.BookmarkViewModel

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkViewModel = BookmarkViewModel(activity?.applicationContext as Application)

        binding.rvBookmark.layoutManager = GridLayoutManager(context, 2)
        bookmarkViewModel.getAllBookmark().observe(viewLifecycleOwner, {
            val bookmarkAdapter = BookmarkAdapter(it)
            if (it.isEmpty()) {
                Toast.makeText(context, "There is no bookmark", Toast.LENGTH_LONG).show()
            } else {
                bookmarkAdapter.setOnItemClickCallback(object : BookmarkAdapter.ItemClickCallback {
                    override fun onItemClicked(movie: Movie) {
                        val toDetail = Intent(requireActivity(), DetailActivity::class.java)
                        toDetail.putExtra("detail", movie)
                        requireActivity().startActivity(toDetail)
                    }
                })
            }
            binding.rvBookmark.adapter = bookmarkAdapter
        })

    }
}