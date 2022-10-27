package com.github.andrewhossam.udacitysession3.shoe_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.andrewhossam.udacitysession3.database.Shoe
import com.github.andrewhossam.udacitysession3.databinding.ShoeItemBinding

class ShoeListAdapter : ListAdapter<Shoe, ShoeAdapter.ShoeViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ShoeAdapter.ShoeViewHolder, position: Int) {
        val current = getItem(position)

        holder.name.text = current.name
        holder.size.text = current.size.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeAdapter.ShoeViewHolder {
        val binding = ShoeItemBinding.inflate(LayoutInflater.from(parent.context))
        return ShoeAdapter.ShoeViewHolder(binding)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Shoe>() {
            override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem == newItem
            }
        }
    }
}