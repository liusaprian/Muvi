package com.tiketgrup1.muvi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tiketgrup1.muvi.databinding.CastItemBinding
import com.tiketgrup1.muvi.model.Cast
import com.tiketgrup1.muvi.utils.ResponseConfig

class CastAdapter(private val data: ArrayList<Cast>) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(private val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Cast) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("${ResponseConfig.IMAGE_BASE_URL}/${ResponseConfig.IMAGE_FILE_SIZE}${data.profilePath}")
                    .into(tvCastProfilePic)
                tvCastName.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}