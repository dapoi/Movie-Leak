package com.dapascript.movieleak.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dapascript.movieleak.databinding.ItemCastBinding
import com.dapascript.movieleak.domain.model.Cast

class CastAdapter : ListAdapter<Cast, CastAdapter.CastViewHolder>(CastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CastViewHolder(private val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.apply {
                tvActorName.text = cast.name
                tvCharacterName.text = cast.character ?: ""

                val profileUrl = if (cast.profilePath != null) {
                    "https://image.tmdb.org/t/p/w200${cast.profilePath}"
                } else {
                    null
                }

                ivActorPhoto.load(profileUrl) {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.ic_menu_gallery)
                }
            }
        }
    }

    private class CastDiffCallback : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }
}
