package com.mosesaltruism.cocktails.core.common.helper

import android.graphics.Bitmap
import androidx.palette.graphics.Palette
import timber.log.Timber

/**
 * Color extraction from image
 * */
fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

fun dynamicImageColorExtraction(
    imageView: Bitmap,
): Palette? {
    return try {
        createPaletteSync(imageView)
    } catch (e: Exception) {
        Timber.d("altruism : could not extract colors")
        null
    }
}