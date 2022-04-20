package com.km.sneakerz.models

import android.graphics.drawable.Drawable
import java.io.Serializable

data class Shoe(
    var id: String,
    var name: String,
    var price: Double,
    var currency: String,
    var description: String,
    var sizeOptions: List<SizeOption>,
    var colorOptions: List<ColorOption>,
    var dominantColor: Int,
    var image: Drawable?,
): Serializable

data class SizeOption(var size: Int, var available: Boolean = true)

data class ColorOption(var color: String, var image: Drawable, var available: Boolean = true)