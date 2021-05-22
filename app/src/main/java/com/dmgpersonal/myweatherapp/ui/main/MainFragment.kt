package com.dmgpersonal.myweatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmgpersonal.myweatherapp.*
import com.dmgpersonal.myweatherapp.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it as AppState) })
        viewModel.getWeatherFromRemoteSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                binding.loadingLayout.visibility = View.GONE
                setData(weatherData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeatherFromLocalSource() } // :)
                    .show()
            }
        }
    }

    private fun setData(weatherData: Weather) {
        binding.locationName.text = weatherData.city.city
        when (weatherData.clouds.intensity) {
            Intensity.NONE -> {
                binding.weatherIcon.setImageResource(R.drawable.sun_icon)
                binding.weatherText.text = getString(R.string.clear)
            }
            Intensity.LOW -> {
                binding.weatherIcon.setImageResource(R.drawable.sun_cloudy_icon)
                binding.weatherText.text = getString(R.string.partly_cloudy)
            }
            Intensity.REGULAR, Intensity.HIGH -> {
                binding.weatherIcon.setImageResource(R.drawable.cloudy_icon)
                binding.weatherText.text = getString(R.string.cloudy)
            }
        }
        // TODO: реализовать настройки и добавить выбор едениц измерения
        (weatherData.temperature.toString() + getString(R.string.celcius)).also {
            binding.temperature.text = it
        }
        (weatherData.wind.speed.toString() + getString(R.string.ms)).also {
            binding.windyText.text = it
        }
        (weatherData.humidity.toString() + getString(R.string.percent)).also {
            binding.waterText.text = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}