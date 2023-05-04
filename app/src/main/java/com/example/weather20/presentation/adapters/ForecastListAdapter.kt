package com.example.weather20.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.decode.SvgDecoder
import coil.load
import com.example.weather20.databinding.ForecastDayItemBinding
import com.example.weather20.entity.resultobjects.Forecasts
import com.example.weather20.presentation.HomelikeViewModel
import com.example.weather20.presentation.IconLoadImage.loadIcon
import javax.inject.Inject

class ForecastListAdapter @Inject constructor(
   // private val onClick: (Forecasts) -> Unit
) : ListAdapter<Forecasts, ForecastViewHolder>(DifUtilCallBack()) {

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

        with(holder.binding) {
            tvDate.text = item.date
            tvMaxTemp.text = item.parts!!.day_short!!.temp.toString()
            tvMinTemp.text = item.parts.day_short!!.temp_min.toString()
            loadIcon(item.parts.day_short.icon.toString(), ivIcon)
        }

        //holder.binding.root.setOnClickListener { onClick(item) }
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<Forecasts>() {
    override fun areItemsTheSame(
        oldItem: Forecasts,
        newItem: Forecasts
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: Forecasts,
        newItem: Forecasts
    ): Boolean {
        return oldItem == newItem
    }
}