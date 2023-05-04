package com.example.weather20.presentation

import androidx.appcompat.widget.AppCompatImageView
import coil.decode.SvgDecoder
import coil.load

object IconLoadImage {
    fun loadIcon(icon: String, view: AppCompatImageView) =
        view.load("https://yastatic.net/weather/i/icons/funky/dark/$icon.svg") {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
}