package com.km.sneakerz.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.sneakerz.R
import com.km.sneakerz.databinding.MainFragmentBinding
import com.km.sneakerz.main.adapters.CategoryAdapter
import com.km.sneakerz.main.adapters.ShoeAdapter
import com.km.sneakerz.models.ColorOption
import com.km.sneakerz.models.Shoe
import com.km.sneakerz.models.SizeOption
import java.util.*

class MainFragment : Fragment() {

    private var shoes = mutableListOf<Shoe>()
    private var categories = mutableListOf<String>("Air Max", "Basketball", "Football", "Casual", "New", "Trending")
    private lateinit var binding: MainFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val sizeOptions = listOf<SizeOption>(SizeOption(41), SizeOption(42), SizeOption(43, false), SizeOption(44, false), SizeOption(45))
        val colorOptions = listOf(ColorOption("RED"), ColorOption("BLUE"), ColorOption("YELLOW"), ColorOption("BLACK"))
        shoes.add(Shoe(UUID.randomUUID().toString(), "Alpha Savage", 197.0, "€", getString(R.string.lorem_ipsum), sizeOptions, colorOptions, R.color.red, ContextCompat.getDrawable(requireContext(), R.drawable.red_shoe)))
        shoes.add(Shoe(UUID.randomUUID().toString(), "Air Max 97", 280.0, "€", getString(R.string.lorem_ipsum), sizeOptions, colorOptions, R.color.yellow, ContextCompat.getDrawable(requireContext(), R.drawable.yellow_show)))
        shoes.add(Shoe(UUID.randomUUID().toString(), "KD13 EP", 220.0, "€", getString(R.string.lorem_ipsum), sizeOptions, colorOptions, R.color.blue, ContextCompat.getDrawable(requireContext(), R.drawable.blue_shoe)))
        shoes.add(Shoe(UUID.randomUUID().toString(), "Air Presto", 185.0, "€", getString(R.string.lorem_ipsum), sizeOptions, listOf(), R.color.teal_700, ContextCompat.getDrawable(requireContext(), R.drawable.air_presto)))
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){
        binding.categoriesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryAdapter(categories)
        }

        binding.shoesList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ShoeAdapter(shoes) { item, binding ->
                val dest = MainFragmentDirections.viewShoeDetails(item)
                val extras = FragmentNavigatorExtras(
                    binding.shoeImage to "shoeImage_${item.id}",
                    binding.price to "price_${item.id}",
                    binding.name to "name_${item.id}"
                )
                findNavController().navigate(dest, extras)
            }
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

}