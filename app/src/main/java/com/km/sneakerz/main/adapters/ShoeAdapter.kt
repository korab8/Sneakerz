package com.km.sneakerz.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.km.sneakerz.databinding.ShoeItemBinding
import com.km.sneakerz.models.Shoe

class ShoeAdapter(private val shoes: MutableList<Shoe>, private var onClick: (Shoe, ShoeItemBinding) -> Unit) :
    RecyclerView.Adapter<ShoeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ShoeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(shoes[position])

    override fun getItemCount(): Int = shoes.size

    inner class ViewHolder(private val binding: ShoeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Shoe) {

            ViewCompat.setTransitionName(binding.shoeImage, "shoeImage_${item.id}")
            ViewCompat.setTransitionName(binding.name, "name_${item.id}")
            ViewCompat.setTransitionName(binding.price, "price_${item.id}")

            with(binding) {
                name.text = item.name
                price.text = "${item.price} ${item.currency}"
                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        root.context,
                        item.dominantColor
                    )
                )
                item.image?.let {
                    shoeImage.setImageDrawable(it)
                }
            }

            binding.root.setOnClickListener { onClick(item, binding) }
        }
    }

}