package com.example.weather20.presentation.detailinfofragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weather20.data.dto.forecastdto.HoursDto
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.databinding.ForecastDayItemBinding
import com.example.weather20.presentation.adapters.ForecastViewHolder
import com.example.weather20.presentation.extensions.loadIcon
import com.example.weather20.presentation.translations.Translations
import javax.inject.Inject

class HoursListAdapter @Inject constructor() : ListAdapter<HoursDto, HoursViewHolder>(DifUtilCallBack()) {
    var position: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoursViewHolder {
        return HoursViewHolder(
            ForecastDayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HoursViewHolder, position: Int) {
        val item = getItem(position)
        this.position = position
        val current = item.temp
        with(holder.binding) {
            tvDate.text = Translations().timeInReadableForm(item)
            tvMaxTemp.text = if (current < 0) "-" + item.temp.toString() + "C°" else item.temp.toString() + "C°"
            tvMinTemp.text = if (current < 0) "-" + item.feels_like.toString() + "C°" else item.feels_like.toString() + "C°"
            ivIcon.loadIcon(item.icon)
        }
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<HoursDto>() {
    override fun areItemsTheSame(
        oldItem: HoursDto,
        newItem: HoursDto
    ): Boolean {
        return oldItem.hour == newItem.hour
    }

    override fun areContentsTheSame(
        oldItem: HoursDto,
        newItem: HoursDto
    ): Boolean {
        return oldItem == newItem
    }
}