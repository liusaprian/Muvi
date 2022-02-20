package com.tiketgrup1.muvi.ui

import android.app.Application
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

        // Test add bookmark item
        testAddBookmark()

        binding.rvBookmark.layoutManager = GridLayoutManager(context, 2)
        bookmarkViewModel.getAllBookmark().observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                Toast.makeText(context, "There is no bookmark", Toast.LENGTH_LONG).show()
            } else {
                val bookmarkAdapter = BookmarkAdapter(it)
                bookmarkAdapter.setOnItemClickCallback(object : BookmarkAdapter.ItemClickCallback {
                    override fun onItemClicked(movie: Movie) {
                        // go to detail movie page
                    }
                })
                binding.rvBookmark.adapter = bookmarkAdapter
            }
        })

    }

    private fun testAddBookmark() {
        val listBookmark = mutableListOf<Movie>()
        listBookmark.add(
            Movie(
                "item.description",
                "item.backdrop",
                "item.title",
                "1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "item.releaseDate",
                 5.0,
                1,
                false
            )
        )
        listBookmark.add(
            Movie(
                "item.description",
                "item.backdrop",
                "item.title",
                "1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "item.releaseDate",
                 5.0,
                2,
                false
            )
        )
        listBookmark.add(
            Movie(
                "item.description",
                "item.backdrop",
                "item.title",
                "1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "item.releaseDate",
                 5.0,
                3,
                false
            )
        )
        listBookmark.add(
            Movie(
                "item.description",
                "item.backdrop",
                "item.title",
                "1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "item.releaseDate",
                 5.0,
                4,
                false
            )
        )
        listBookmark.add(
            Movie(
                "item.description",
                "item.backdrop",
                "item.title",
                "1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "item.releaseDate",
                 5.0,
                5,
                false
            )
        )
        for (item in listBookmark) {
            bookmarkViewModel.insertBookmark(item)
        }
    }
}