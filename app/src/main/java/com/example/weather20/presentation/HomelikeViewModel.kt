package com.example.weather20.presentation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import coil.decode.SvgDecoder
import coil.load
import com.example.weather20.R
import com.example.weather20.State
import com.example.weather20.data.ForecastRepository
import com.example.weather20.databinding.FragmentHomelikeBinding
import com.example.weather20.entity.Results
import com.example.weather20.entity.resultobjects.Fact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class HomelikeViewModel @Inject constructor(private var forecastRepository: ForecastRepository) :
    ViewModel() {

    private var _state = MutableStateFlow<State>(State.Success)
    var state = _state.asStateFlow()

    private var _error = Channel<String>()
    var error = _error.receiveAsFlow()

    suspend fun forecastInfo(lat: Double, lon: Double): Results =
        forecastRepository.getForecastInfo(lat, lon)

    @SuppressLint("SetTextI18n")
    suspend fun refreshForecast(
        binding: FragmentHomelikeBinding,
        lat: Double,
        lon: Double,
        activity: Activity,
        fact: Fact?
    ) {
        val currentTemp = forecastInfo(lat, lon).fact?.temp
        with(binding) {
            _state.value = State.Loading
            stateInfo(binding)
            tvCondition.text = translateConditionToRu(fact, activity)
            tvTime.text = forecastInfo(lat, lon).forecasts?.first()?.date.toString()
            tvCurrentTemp.text =
                if (currentTemp!! < 0) "-$currentTemp C°" else "$currentTemp C°"
            loadIcon(
                forecastInfo(lat, lon).fact?.icon.toString(),
                binding.iconDashboardCurrentTemp
            )
            tvWindSpeed.text = "${forecastInfo(lat, lon).fact?.wind_speed.toString()} м/с"
            tvFeelsLike.text = activity.getString(R.string.feels_like) + ": " + forecastInfo(
                lat,
                lon
            ).fact?.feels_like.toString() + " C°"
            tvHumidity.text = "${forecastInfo(lat, lon).fact?.humidity.toString()} %"
            tvMinMax.text = activity.getString(R.string.min_temp) + ": " + forecastInfo(
                lat,
                lon
            ).forecasts?.first()?.parts?.day_short
                ?.temp_min.toString() + " C°," + activity.getString(R.string.max_temp) + ": " + forecastInfo(
                lat, lon
            ).forecasts?.first()?.parts?.day_short?.temp_min.toString() + " C°"
            _state.value = State.Success
            stateInfo(binding)
        }
    }

    private fun stateInfo(binding: FragmentHomelikeBinding) {
        when (_state.value) {
            State.Loading -> with(binding) {
                lottieLoading.isVisible = true
                lottieLoading.playAnimation()
                cardViewForecast.isVisible = false
            }

            State.Success -> with(binding) {
                lottieLoading.isVisible = false
                lottieLoading.pauseAnimation()
                cardViewForecast.isVisible = true
                lottieBackground.isVisible = true
            }

            else -> {
                _state.value = State.Error("Exception")
            }
        }
    }

    fun translateConditionToRu(fact: Fact?, activity: Activity) = when (fact?.condition) {
        "clear" -> activity.getString(R.string.clear)
        "partly-cloudy" -> activity.getString(R.string.partly_cloudy)
        "cloudy" -> activity.getString(R.string.cloudy)
        "overcast" -> activity.getString(R.string.overcast)
        "drizzle" -> activity.getString(R.string.drizzle)
        "light-rain" -> activity.getString(R.string.light_rain)
        "rain" -> activity.getString(R.string.rain)
        "moderate-rain" -> activity.getString(R.string.moderate_rain)
        "heavy-rain" -> activity.getString(R.string.heavy_rain)
        "continuous-heavy-rain" -> activity.getString(R.string.continuous_heavy_rain)
        "showers" -> activity.getString(R.string.showers)
        "wet-snow" -> activity.getString(R.string.wet_snow)
        "light-snow" -> activity.getString(R.string.light_snow)
        "snow" -> activity.getString(R.string.snow)
        "snow-showers" -> activity.getString(R.string.snow_showers)
        "hail" -> activity.getString(R.string.hail)
        "thunderstorm" -> activity.getString(R.string.thunderstorm)
        "thunderstorm-with-rain" -> activity.getString(R.string.thunderstorm_with_rain)
        else -> {
            activity.getString(R.string.thunderstorm_with_hail)
        }
    }


    private fun loadIcon(icon: String, view: AppCompatImageView) =
        view.load("https://yastatic.net/weather/i/icons/funky/dark/$icon.svg") {
            decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
        }
}
