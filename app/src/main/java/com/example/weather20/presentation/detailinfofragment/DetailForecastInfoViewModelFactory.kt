package com.example.weather20.presentation.detailinfofragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class DetailForecastInfoViewModelFactory @Inject constructor(private val detailForecastInfoViewModel: DetailForecastInfoViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(DetailForecastInfoViewModel::class.java)
        ) detailForecastInfoViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}