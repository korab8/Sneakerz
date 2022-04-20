package com.km.sneakerz.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.km.sneakerz.databinding.CategoryItemBinding

class CategoryAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(categories[position])

    override fun getItemCount(): Int = categories.size

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) {
            binding.item.text = category
        }
    }
}