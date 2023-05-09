package com.example.weather20.presentation.detailinfofragment

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather20.R
import com.example.weather20.State
import com.example.weather20.data.dto.forecastdto.HoursDto
import com.example.weather20.databinding.FragmentDetailForecastInfoBinding
import com.example.weather20.domain.usecases.GetForecastsUseCase
import com.example.weather20.presentation.extensions.loadIcon
import com.example.weather20.presentation.translations.Translations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailForecastInfoViewModel @Inject constructor(
    private val getForecastsUseCase: GetForecastsUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    private var _error = Channel<String>()
    var error = _error.receiveAsFlow()

    private var _hours = MutableStateFlow<List<HoursDto>>(emptyList())
    val hours = _hours.asStateFlow()

    @SuppressLint("SetTextI18n")
    suspend fun refreshForecastHours(
        binding: FragmentDetailForecastInfoBinding,
        lat: Double,
        lon: Double,
        activity: Activity,
        position: Int
    ) {

        val forecast = getForecastsUseCase.execute(lat, lon)[position].parts!!.day_short

        viewModelScope.launch {
            _state.value = State.Loading
            try {
                with(binding) {
                    _state.value = State.Loading
                    tvCondition.text =
                        Translations().translateCondition(forecast.condition, activity)
                    tvTime.text = getForecastsUseCase.execute(lat, lon)[position].date
                    tvCurrentTemp.text = "${forecast.temp} C°"
                    binding.iconDashboardCurrentTemp.loadIcon(forecast.icon)
                    tvWindSpeed.text = "${forecast.wind_speed} м/с"
                    tvFeelsLike.text =
                        activity.getString(R.string.feels_like_future) + ": " + forecast.feels_like.toString() + " C°"
                    tvHumidity.text = "${forecast.humidity} %"
                    tvMinMax.text =
                        activity.getString(R.string.min_temp) + ": " + forecast.temp_min.toString() + " C°," + activity.getString(
                            R.string.max_temp
                        ) + ": " + forecast.temp.toString() + " C°"
                    forecastsLoader(lat, lon, position)
                    _state.value = State.Success
                }
            } catch (e: Exception) {
                _state.value = State.Error("No internet connection or location enabled")
            }
        }
    }

    private fun forecastsLoader(lat: Double, lon: Double, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getForecastsUseCase.execute(lat, lon)[position].hours
            }.fold(
                onSuccess = {
                    _hours.value = it
                },
                onFailure = {
                    Log.d("DetailForecastInfoViewModel", "forecast: ${it.message ?: ""}")
                }
            )
        }
    }
}