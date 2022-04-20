package com.km.sneakerz.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.transition.TransitionInflater
import com.google.android.material.chip.Chip
import com.km.sneakerz.R
import com.km.sneakerz.databinding.DetailsFragmentBinding
import com.km.sneakerz.models.Shoe

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding
    private lateinit var shoe: Shoe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        shoe = arguments?.getSerializable("shoe") as Shoe
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){
        with(binding){
            shoeImage.setImageDrawable(shoe.image)
            title.text = shoe.name
            price.text = "${shoe.price} ${shoe.currency}"
            circle.setBackgroundColor(ContextCompat.getColor(requireContext(), shoe.dominantColor))
            description.apply {
                text = shoe.description
                setOnClickListener {
                    description.toggle()
                }
                setAnimationDuration(100)
            }
            for(size in shoe.sizeOptions){
                val chip = Chip(requireContext())
                chip.text = size.size.toString()
                chip.isEnabled = size.available
                sizeChipGroup.addView(chip)
            }
        }

        ViewCompat.setTransitionName(binding.title, "name_${shoe.id}")
        ViewCompat.setTransitionName(binding.shoeImage, "shoeImage_${shoe.id}")
        ViewCompat.setTransitionName(binding.price, "price_${shoe.id}")
    }

}