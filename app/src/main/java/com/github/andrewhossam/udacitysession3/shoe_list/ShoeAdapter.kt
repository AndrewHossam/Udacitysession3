package com.github.andrewhossam.udacitysession3.shoe_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.andrewhossam.udacitysession3.database.Shoe
import com.github.andrewhossam.udacitysession3.databinding.ShoeItemBinding

class ShoeAdapter : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    var data = listOf<Shoe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val current = data[position]

        holder.name.text = current.name
        holder.size.text = current.size.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ShoeItemBinding.inflate(LayoutInflater.from(parent.context))
        return ShoeViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    class ShoeViewHolder(binding: ShoeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvName
        val size = binding.tvSize
    }
}
