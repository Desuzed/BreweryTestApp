package com.desuzed.brewerytestapp.ui.brewery_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.desuzed.brewerytestapp.App
import com.desuzed.brewerytestapp.databinding.FragmentBreweryBinding
import com.desuzed.brewerytestapp.ui.ViewModelFactory

class BreweryFragment : Fragment() {
    private lateinit var binding: FragmentBreweryBinding
    private val breweryViewModel: BreweryViewModel by lazy {
        ViewModelProvider(
            requireActivity(), ViewModelFactory(
                App.instance.getRepo()
            )
        ).get(BreweryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreweryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(BREWERY_ID)
        if (id != null){
            breweryViewModel.fetchBrewery(id)
        }
        lifecycleScope.launchWhenCreated {
            breweryViewModel.breweryStateFlow.collect{
                binding.nameTextView.text = it.name
                binding.typeTextView.text = it.breweryType
                binding.countryTextView.text = it.country
                binding.stateTextView.text = it.state
                binding.cityTextView.text = it.city
                binding.streetTextView.text = it.street
                binding.address2TextView.text = it.address2
                binding.address3ProvinceTextView.text = it.address3
                binding.postalCodeTextView.text = it.postalCode
                binding.latitudeTextView.text = it.latitude
                binding.longitudeTextView.text = it.longitude
                binding.phoneTextView.text = it.postalCode
                binding.websiteUrlProvinceTextView.text = it.websiteUrl
                binding.createdAtTextView.text = it.createdAt
                binding.updatedAtCodeTextView.text = it.updatedAt
            }
        }
    }

    companion object {
        const val BREWERY_ID = "BREWERY_ID"
    }

}