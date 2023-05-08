package com.example.weather20.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.databinding.ForecastDayItemBinding
import com.example.weather20.presentation.extensions.loadIcon
import javax.inject.Inject

class ForecastAdapter @Inject constructor() : Adapter<ForecastViewHolder>() {
    private var forecastsData: List<ForecastsDto> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ForecastsDto>) {
        this.forecastsData = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ForecastDayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = forecastsData.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecasts = forecastsData.getOrNull(position)!!
        with(holder.binding) {
            tvDate.text = forecasts.date
            tvMaxTemp.text = forecasts.parts!!.day_short.temp.toString()
            tvMinTemp.text = forecasts.parts.day_short.temp_min.toString()
            ivIcon.loadIcon(forecasts.parts.day_short.icon)
        }
    }
}

class ForecastViewHolder(val binding: ForecastDayItemBinding) : ViewHolder(binding.root)