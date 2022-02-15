package com.desuzed.brewerytestapp.ui.brewery_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.desuzed.brewerytestapp.App
import com.desuzed.brewerytestapp.R
import com.desuzed.brewerytestapp.databinding.FragmentBreweryBinding
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.pojo.Error
import com.desuzed.brewerytestapp.ui.Event
import com.desuzed.brewerytestapp.ui.ViewModelFactory
import com.desuzed.brewerytestapp.ui.toggleButtonVisibility
import com.desuzed.brewerytestapp.ui.toggleRefresh

class BreweryFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
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
        fetchBreweryById()
        observe()
        setOnClickListener()
        binding.brewerySwipeRefresh.setOnRefreshListener(this)
    }

    private fun fetchBreweryById() {
        val id = arguments?.getString(BREWERY_ID)
        if (id != null) {
            breweryViewModel.fetchBrewery(id)
            toggleRefresh(binding.brewerySwipeRefresh, true)
            toggleButtonVisibility(binding.refreshBreweryButton, false)
        }
    }

    private fun setOnClickListener() {
        binding.refreshBreweryButton.setOnClickListener {
            fetchBreweryById()
        }
    }

    private fun observe() {
        breweryViewModel.breweryLiveData.observe(viewLifecycleOwner, breweryObserver)
        breweryViewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private val breweryObserver = Observer<Brewery> {
        updateUi(it)
        toggleRefresh(binding.brewerySwipeRefresh, false)
        toggleButtonVisibility(binding.refreshBreweryButton, false)
        binding.errorTextView.visibility = View.GONE
    }

    private val errorObserver = Observer <Event<Error>> { event ->
        val content = event.getContentIfNotHandled()
        if (content != null) {
            // toast(content.message.toString())
            toggleRefresh(binding.brewerySwipeRefresh, false)
            toggleButtonVisibility(binding.refreshBreweryButton, true)
            binding.errorTextView.text = content.message
            binding.errorTextView.visibility = View.VISIBLE

        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi (brewery : Brewery) {
        binding.nameTextView.text = resources.getString(R.string.name) + ": ${brewery.name}"
        binding.typeTextView.text =
            resources.getString(R.string.brewery_type) + ": ${brewery.breweryType}"
        binding.countryTextView.text =
            resources.getString(R.string.country) + ": ${brewery.country}"
        binding.countyProvinceTextView.text =
            resources.getString(R.string.country_province) + ": ${brewery.countyProvince}"
        binding.stateTextView.text = resources.getString(R.string.state) + ": ${brewery.state}"
        binding.cityTextView.text = resources.getString(R.string.city) + ": ${brewery.city}"
        binding.streetTextView.text = resources.getString(R.string.street) + ": ${brewery.street}"
        binding.address2TextView.text =
            resources.getString(R.string.address_2) + ": ${brewery.address2}"
        binding.address3ProvinceTextView.text =
            resources.getString(R.string.address_3) + ": ${brewery.address3}"
        binding.postalCodeTextView.text =
            resources.getString(R.string.postal_code) + ": ${brewery.postalCode}"
        binding.latitudeTextView.text =
            resources.getString(R.string.latitude) + ": ${brewery.latitude}"
        binding.longitudeTextView.text =
            resources.getString(R.string.longitude) + ": ${brewery.longitude}"
        binding.phoneTextView.text =
            resources.getString(R.string.postal_code) + ": ${brewery.postalCode}"
        binding.websiteUrlTextView.text =
            resources.getString(R.string.website_url) + ": ${brewery.websiteUrl}"
        binding.createdAtTextView.text =
            resources.getString(R.string.created_at) + ": ${brewery.createdAt}"
        binding.updatedAtCodeTextView.text =
            resources.getString(R.string.updated_at) + ": ${brewery.updatedAt}"
    }


    override fun onDestroy() {
        super.onDestroy()
        breweryViewModel.breweryLiveData.removeObserver(breweryObserver)
        breweryViewModel.errorLiveData.removeObserver(errorObserver)
    }

    override fun onRefresh() {
        fetchBreweryById()
    }


    companion object {
        const val BREWERY_ID = "BREWERY_ID"
    }

}