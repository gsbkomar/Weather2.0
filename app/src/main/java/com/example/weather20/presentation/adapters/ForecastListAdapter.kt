package com.example.weather20.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weather20.data.dto.resultsdto.ForecastsDto
import com.example.weather20.databinding.ForecastDayItemBinding
import com.example.weather20.presentation.extensions.loadIcon
import javax.inject.Inject

class ForecastListAdapter @Inject constructor(
    private val onClick: (item: Int) -> Unit
) : ListAdapter<ForecastsDto, ForecastViewHolder>(DifUtilCallBack()) {
    var position: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(
            ForecastDayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = getItem(position)
        this.position = position

        with(holder.binding) {
            tvDate.text = item.date
            tvMaxTemp.text = "${item.parts!!.day_short.temp} C°"
            tvMinTemp.text = "${item.parts!!.day_short.temp_min} C°"
            ivIcon.loadIcon(item.parts!!.day_short.icon.toString())
        }

        holder.binding.root.setOnClickListener { onClick(position) }
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<ForecastsDto>() {
    override fun areItemsTheSame(
        oldItem: ForecastsDto,
        newItem: ForecastsDto
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: ForecastsDto,
        newItem: ForecastsDto
    ): Boolean {
        return oldItem == newItem
    }
}