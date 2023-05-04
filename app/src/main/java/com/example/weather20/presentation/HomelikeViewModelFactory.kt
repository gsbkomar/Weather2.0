package com.example.weather20.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class HomelikeViewModelFactory @Inject constructor(private val homelikeViewModel: HomelikeViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(HomelikeViewModel::class.java)
        ) homelikeViewModel as T else throw java.lang.IllegalArgumentException("Unknown class name")
}