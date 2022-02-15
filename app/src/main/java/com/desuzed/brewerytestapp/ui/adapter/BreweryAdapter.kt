package com.desuzed.brewerytestapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.desuzed.brewerytestapp.databinding.BreweryItemBinding
import com.desuzed.brewerytestapp.model.pojo.Brewery


class BreweryAdapter(
    private val onItemClickListener: OnBreweryItemClickListener,
) :
    ListAdapter<Brewery, BreweryAdapter.BreweryViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BreweryViewHolder {
        return BreweryViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: BreweryViewHolder,
        position: Int
    ) {
        val current = getItem(position)
        holder.bind(current, onItemClickListener)
    }

    class BreweryViewHolder(private val binding: BreweryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(current: Brewery, onItemClickListener: OnBreweryItemClickListener) {
            binding.nameTextView.text = current.name
            binding.typeTextView.text = current.breweryType
            binding.countryTextView.text = current.country
            itemView.setOnClickListener {
                onItemClickListener.onClick(current)
            }
        }

        companion object {
            fun create(parent: ViewGroup): BreweryViewHolder {
                return BreweryViewHolder(
                    BreweryItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    class UserComparator : DiffUtil.ItemCallback<Brewery>() {
        override fun areContentsTheSame(
            oldItem: Brewery,
            newItem: Brewery
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(
            oldItem: Brewery,
            newItem: Brewery
        ): Boolean {
            return oldItem == newItem
        }
    }

}

interface OnBreweryItemClickListener {
    fun onClick(brewery: Brewery)
}