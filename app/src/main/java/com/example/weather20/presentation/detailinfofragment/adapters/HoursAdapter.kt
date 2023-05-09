package com.example.weather20.presentation.detailinfofragment.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather20.data.dto.forecastdto.HoursDto
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.databinding.ForecastDayItemBinding
import com.example.weather20.presentation.extensions.loadIcon
import com.example.weather20.presentation.translations.Translations
import javax.inject.Inject

class HoursAdapter @Inject constructor() : RecyclerView.Adapter<HoursViewHolder>() {
    private var forecastsData: List<HoursDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder(
            ForecastDayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = forecastsData.size

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        val forecasts = forecastsData.getOrNull(position)!!
        with(holder.binding) {
            tvDate.text = Translations().timeInReadableForm(forecasts)
            tvMaxTemp.text = "${forecasts.temp.toString()} C°"
            tvMinTemp.text = "${forecasts.feels_like.toString()} C°"
            ivIcon.loadIcon(forecasts.icon)
        }
    }
}

class HoursViewHolder(val binding: ForecastDayItemBinding) : RecyclerView.ViewHolder(binding.root)