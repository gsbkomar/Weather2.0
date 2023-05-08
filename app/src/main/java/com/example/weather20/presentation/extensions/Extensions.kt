package com.example.weather20.presentation.extensions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.decode.SvgDecoder
import coil.load

fun Fragment.isPermissionsGranted(permissions: String): Boolean {
    return ContextCompat.checkSelfPermission(
        activity as AppCompatActivity, permissions
    ) == PackageManager.PERMISSION_GRANTED
}

fun AppCompatImageView.loadIcon(icon: String) =
    load("https://yastatic.net/weather/i/icons/funky/dark/$icon.svg") {
        decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
    }