package com.tiketgrup1.muvi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tiketgrup1.muvi.databinding.ItemBookmarkBinding
import com.tiketgrup1.muvi.model.Movie
import com.tiketgrup1.muvi.utils.ResponseConfig

class BookmarkAdapter(private val bookmarkList: List<Movie>) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    private lateinit var itemClickCallback: ItemClickCallback

    inner class ViewHolder(private val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            Glide.with(itemView.context)
                .load("${ResponseConfig.IMAGE_BASE_URL}/${ResponseConfig.IMAGE_FILE_SIZE}/${item.posterPath}")
                .into(binding.ivBookmarkItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookmarkList[position])
        holder.itemView.setOnClickListener {
            itemClickCallback.onItemClicked(bookmarkList[position])
        }
    }

    override fun getItemCount(): Int = bookmarkList.size

    fun setOnItemClickCallback(itemClickCallback: ItemClickCallback) {
        this.itemClickCallback = itemClickCallback
    }

    interface ItemClickCallback {
        fun onItemClicked(movie: Movie)
    }


}