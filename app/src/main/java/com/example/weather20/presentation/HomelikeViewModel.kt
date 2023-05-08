package com.example.weather20.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather20.R
import com.example.weather20.State
import com.example.weather20.data.dto.ResultsDto
import com.example.weather20.data.dto.resultsdto.FactDto
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.databinding.FragmentHomelikeBinding
import com.example.weather20.domain.GetForecastUseCase
import com.example.weather20.presentation.extensions.loadIcon
import com.example.weather20.presentation.translations.Translations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomelikeViewModel @Inject constructor(private var getForecastUseCase: GetForecastUseCase) :
    ViewModel() {

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    private var _error = Channel<String>()
    var error = _error.receiveAsFlow()

    private var _forecasts = MutableStateFlow<List<ForecastsDto>>(emptyList())
    val forecasts = _forecasts.asStateFlow()

    suspend fun forecastInfo(lat: Double, lon: Double): ResultsDto =
        getForecastUseCase.getForecastInfo(lat, lon)

    @SuppressLint("SetTextI18n")
    suspend fun refreshForecast(
        binding: FragmentHomelikeBinding,
        lat: Double,
        lon: Double,
        activity: Activity,
        fact: FactDto
    ) {
        val currentTemp = forecastInfo(lat, lon).fact.temp

        viewModelScope.launch {
            _state.value = State.Loading
            try {
                with(binding) {
                    _state.value = State.Loading
                    tvCondition.text = Translations().translateCondition(fact.condition, activity)
                    tvTime.text = forecastInfo(lat, lon).forecasts.first().date
                    tvCurrentTemp.text =
                        if (currentTemp < 0) "-$currentTemp C°" else "$currentTemp C°"
                    binding.iconDashboardCurrentTemp.loadIcon(forecastInfo(lat, lon).fact.icon)
                    tvWindSpeed.text = "${forecastInfo(lat, lon).fact.wind_speed} м/с"
                    tvFeelsLike.text =
                        activity.getString(R.string.feels_like) + ": " + forecastInfo(
                            lat,
                            lon
                        ).fact.feels_like.toString() + " C°"
                    tvHumidity.text = "${forecastInfo(lat, lon).fact.humidity} %"
                    tvMinMax.text = activity.getString(R.string.min_temp) + ": " + forecastInfo(
                        lat,
                        lon
                    ).forecasts.first().parts!!.day_short
                        .temp_min.toString() + " C°," + activity.getString(R.string.max_temp) + ": " + forecastInfo(
                        lat,
                        lon
                    )
                        .forecasts.first().parts!!.day_short.temp.toString() + " C°"
                    forecastsLoader(lat, lon)
                    _state.value = State.Success
                }
            } catch (e: Exception) {
                _state.value = State.Error("No internet connection or location enabled")
                Log.d("HomelikeViewModel", e.message.toString())
            }
        }
    }

    private fun forecastsLoader(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getForecastUseCase.getForecastInfo(lat, lon).forecasts
            }.fold(
                onSuccess = {
                    _forecasts.value = it
                },
                onFailure = {
                    Log.d("HomelikeViewModel", "forecast: ${it.message ?: ""}")
                }
            )
        }
    }
}
