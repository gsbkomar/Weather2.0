package com.example.weather20.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ForecastRepositoryImpl
import com.example.domain.domain.entity.resultobjects.Fact
import com.example.weather20.R
import com.example.weather20.State
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.databinding.FragmentHomelikeBinding
import com.example.weather20.presentation.extensions.loadIcon
import com.example.weather20.presentation.translations.Translations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomelikeViewModel @Inject constructor(forecastRepository: ForecastRepositoryImpl) :
    ViewModel() {

    var getResultsForecastUseCase = com.example.domain.domain.usecases.GetResultsForecastUseCase(forecastRepository)
    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    private var _error = Channel<String>()
    var error = _error.receiveAsFlow()

    private var _forecasts = MutableStateFlow<List<ForecastsDto>>(emptyList())
    val forecasts = _forecasts.asStateFlow()

    @SuppressLint("SetTextI18n")
    suspend fun refreshForecast(
        binding: FragmentHomelikeBinding,
        lat: Double,
        lon: Double,
        activity: Activity,
        fact: Fact
    ) {
        val forecast = getResultsForecastUseCase.execute(lat, lon).fact

        viewModelScope.launch {
            _state.value = State.Loading
            try {
                with(binding) {
                    _state.value = State.Loading
                    tvCondition.text = Translations().translateCondition(fact.condition, activity)
                    tvTime.text = getResultsForecastUseCase.execute(lat, lon).forecasts.first().date
                    tvCurrentTemp.text = "${forecast.temp} C°"
                    binding.iconDashboardCurrentTemp.loadIcon(getResultsForecastUseCase.execute(lat, lon).fact.icon)
                    tvWindSpeed.text = "${getResultsForecastUseCase.execute(lat, lon).fact.wind_speed} м/с"
                    tvFeelsLike.text =
                        activity.getString(R.string.feels_like) + ": " + forecast.feels_like.toString() + " C°"
                    tvHumidity.text = "${forecast.humidity} %"
                    tvMinMax.text = activity.getString(R.string.min_temp) + ": " + getResultsForecastUseCase.execute(lat, lon).forecasts.first().parts!!.day_short
                        .temp_min.toString() + " C°," + activity.getString(R.string.max_temp) + ": " + getResultsForecastUseCase.execute(lat, lon)
                        .forecasts.first().parts!!.day_short.temp.toString() + " C°"
                    forecastsLoader(lat, lon)
                    _state.value = State.Success
                }
            } catch (e: Exception) {
                _state.value = State.Error("No internet connection or location enabled")
            }
        }
    }

    private fun forecastsLoader(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getResultsForecastUseCase.execute(lat, lon).forecasts
            }.fold(
                onSuccess = {
                    _forecasts.value = it as List<ForecastsDto>
                },
                onFailure = {
                    Log.d("HomelikeViewModel", "forecast: ${it.message ?: ""}")
                }
            )
        }
    }
}
