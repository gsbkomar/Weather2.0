package com.example.weather20.presentation.detailinfofragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather20.R
import com.example.weather20.State
import com.example.weather20.databinding.FragmentDetailForecastInfoBinding
import com.example.weather20.presentation.Keys.KEY_POSITION
import com.example.weather20.presentation.detailinfofragment.adapters.HoursListAdapter
import com.example.weather20.presentation.factory.DetailForecastInfoViewModelFactory
import com.example.weather20.presentation.managers.DialogManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailForecastInfoFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentDetailForecastInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val hoursListAdapter = HoursListAdapter()

    @Inject
    lateinit var detailForecastInfoViewModelFactory: DetailForecastInfoViewModelFactory
    private val viewModel: DetailForecastInfoViewModel by viewModels { detailForecastInfoViewModelFactory }

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(KEY_POSITION).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailForecastInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()

        with(binding.lottieBackground) {
            setAnimation(R.raw.detail_frag_background)
            playAnimation()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Error -> {
                        viewModel.error.collectLatest { message ->
                            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    State.Loading -> setVisibility(false)
                    State.Success -> setVisibility(true)
                }
            }
        }

        binding.rcHoursFuture.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcHoursFuture.adapter = hoursListAdapter

        lifecycleScope.launch {
            viewModel.hours.onEach {
                hoursListAdapter.submitList(it.toList())
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    private fun setVisibility(isVisible: Boolean) = with(binding) {
        lottieLoading.setAnimation(R.raw.loading)
        lottieLoading.isVisible = !isVisible
        if (!isVisible) lottieLoading.playAnimation() else lottieLoading.pauseAnimation()
        lottieBackground.isVisible = isVisible
        cardViewForecast.isVisible = isVisible
    }

    private fun initial() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        checkLocation()
    }

    private fun checkLocation() {
        if (isLocationEnabled()) {
            getLocationToHoursFragment()
        } else {
            DialogManager.locationSettingsDialog(requireContext(), object : DialogManager.Listener {
                override fun onClick(name: String?) {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }

            })
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getLocationToHoursFragment() {
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener {
                lifecycleScope.launch {
                    viewModel.refreshForecastHours(
                        binding,
                        it.result.latitude,
                        it.result.longitude,
                        requireActivity(),
                        param1!!.toInt()
                    )
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}