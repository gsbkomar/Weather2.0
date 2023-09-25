package com.example.weather20.presentation

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather20.R
import com.example.weather20.State
import com.example.weather20.databinding.FragmentHomelikeBinding
import com.example.weather20.presentation.Keys.KEY_POSITION
import com.example.weather20.presentation.adapters.ForecastListAdapter
import com.example.weather20.presentation.extensions.isPermissionsGranted
import com.example.weather20.presentation.factory.HomelikeViewModelFactory
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
class HomelikeFragment @Inject constructor() : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var _binding: FragmentHomelikeBinding? = null
    private val binding get() = _binding!!
    private lateinit var pLauncher: ActivityResultLauncher<String>

    private var position: Int = 0

    @Inject
    lateinit var homelikeViewModelFactory: HomelikeViewModelFactory
    private val viewModel: HomelikeViewModel by viewModels { homelikeViewModelFactory }

    private val forecastListAdapter = ForecastListAdapter{ onItemClick(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomelikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkStateInfo()
        checkPermission()
        initialLocationClient()

        position = forecastListAdapter.position

        with(binding.lottieBackground) {
            setAnimation(R.raw.background_day)
            playAnimation()
        }

        binding.rcDaysFuture.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcDaysFuture.adapter = forecastListAdapter

        lifecycleScope.launch {
            viewModel.forecasts.onEach {
                forecastListAdapter.submitList(it.toList())
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkStateInfo() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is State.Error -> {
                        viewModel.error.collectLatest { message ->
                            Toast.makeText(requireContext(), message , Toast.LENGTH_SHORT).show()
                        }
                    }
                    State.Loading -> setVisibility(false)


                    State.Success -> setVisibility(true)
                }
            }
        }
    }

    private fun setVisibility(isVisible: Boolean) = with(binding) {
        lottieLoading.setAnimation(R.raw.loading)
        lottieLoading.isVisible = !isVisible
        if (!isVisible) lottieLoading.playAnimation() else lottieLoading.pauseAnimation()
        lottieBackground.isVisible = isVisible
        cardViewForecast.isVisible = isVisible
        rcDaysFuture.isVisible = isVisible
    }

    private fun initialLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        checkLocation()
    }

    private fun checkLocation() {
        if (isLocationEnabled()) {
            getLocation()
        } else {
            DialogManager.locationSettingsDialog(requireContext(), object : DialogManager.Listener {
                override fun onClick(name: String?) {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    getLocation()
                }
            })
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun getLocation() {
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener {
                lifecycleScope.launch {
                    viewModel.refreshForecast(
                        binding,
                        it.result.latitude,
                        it.result.longitude,
                        requireActivity(),
                        viewModel.getResultsForecastUseCase.execute(it.result.latitude, it.result.longitude).fact
                    )
                }
            }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            checkLocation()
        }
    }

    private fun checkPermission() {
        if (!isPermissionsGranted(ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }

    private fun onItemClick(position: Int) {
        findNavController().navigate(
            R.id.action_homelikeFragment_to_detailForecastInfoFragment,
        Bundle().apply {
            putInt( KEY_POSITION , position)
        })
    }
}