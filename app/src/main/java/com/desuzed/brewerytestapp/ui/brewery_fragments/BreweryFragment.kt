package com.desuzed.brewerytestapp.ui.brewery_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.desuzed.brewerytestapp.App
import com.desuzed.brewerytestapp.databinding.FragmentBreweryBinding
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.pojo.Error
import com.desuzed.brewerytestapp.ui.Event
import com.desuzed.brewerytestapp.ui.ViewModelFactory
import com.desuzed.brewerytestapp.ui.toast
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
        binding.brewerySwipeRefresh.setOnRefreshListener(this)
    }

    private fun fetchBreweryById() {
        val id = arguments?.getString(BREWERY_ID)
        if (id != null) {
            breweryViewModel.fetchBrewery(id)
            toggleRefresh(binding.brewerySwipeRefresh, true)
        }
    }



    private fun observe() {
        breweryViewModel.breweryLiveData.observe(viewLifecycleOwner, breweryObserver)
        breweryViewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private val breweryObserver = Observer<Brewery>{
        updateUi(it)
    }

    private val errorObserver = Observer <Event<Error>> { event ->
        val content = event.getContentIfNotHandled()
        if (content != null){
            toast(content.message.toString())
        }
        toggleRefresh(binding.brewerySwipeRefresh, false)
    }

    private fun updateUi (brewery : Brewery){
        binding.nameTextView.text = brewery.name
        binding.typeTextView.text = brewery.breweryType
        binding.countryTextView.text = brewery.country
        binding.stateTextView.text = brewery.state
        binding.cityTextView.text = brewery.city
        binding.streetTextView.text = brewery.street
        binding.address2TextView.text = brewery.address2
        binding.address3ProvinceTextView.text = brewery.address3
        binding.postalCodeTextView.text = brewery.postalCode
        binding.latitudeTextView.text = brewery.latitude
        binding.longitudeTextView.text = brewery.longitude
        binding.phoneTextView.text = brewery.postalCode
        binding.websiteUrlProvinceTextView.text = brewery.websiteUrl
        binding.createdAtTextView.text = brewery.createdAt
        binding.updatedAtCodeTextView.text = brewery.updatedAt
        toggleRefresh(binding.brewerySwipeRefresh, false)
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