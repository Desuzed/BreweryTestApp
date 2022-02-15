package com.desuzed.brewerytestapp.ui.brewery_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.desuzed.brewerytestapp.App
import com.desuzed.brewerytestapp.R
import com.desuzed.brewerytestapp.databinding.FragmentBreweryListBinding
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.pojo.Error
import com.desuzed.brewerytestapp.ui.*
import com.desuzed.brewerytestapp.ui.adapter.BreweryAdapter
import com.desuzed.brewerytestapp.ui.adapter.OnBreweryItemClickListener

class BreweryListFragment : Fragment(), OnBreweryItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: FragmentBreweryListBinding
    private val breweryAdapter = BreweryAdapter(this)
    private val breweryListViewModel: BreweryListViewModel by lazy {
        ViewModelProvider(
            requireActivity(), ViewModelFactory(
                App.instance.getRepo()
            )
        ).get(BreweryListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreweryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fetchBreweriesList()
        observe()
        collectError()
        setOnClickListener()
    }

    private fun fetchBreweriesList() {
        toggleRefresh(binding.breweryListSwipeRefresh, true)
        toggleButtonVisibility(binding.refreshBreweryListButton, false)
        breweryListViewModel.fetchBreweriesList()
    }

    private fun setupViews() {
        binding.breweryRecyclerView.adapter = breweryAdapter
        binding.breweryListSwipeRefresh.setOnRefreshListener(this)
    }

    private fun observe() {
        breweryListViewModel.breweriesListLiveData.observe(viewLifecycleOwner, {
            breweryAdapter.submitList(it)
            toggleRefresh(binding.breweryListSwipeRefresh, false)
            toggleButtonVisibility(binding.refreshBreweryListButton, false)
            binding.errorListTextView.visibility = View.GONE
        })
    }

    private fun setOnClickListener() {
        binding.refreshBreweryListButton.setOnClickListener {
            fetchBreweriesList()
        }
    }

    private val errorObserver = Observer<Event<Error>> { event ->
        val content = event.getContentIfNotHandled()
        if (content != null) {
            // toast(content.message.toString())
            toggleRefresh(binding.breweryListSwipeRefresh, false)
            toggleButtonVisibility(binding.refreshBreweryListButton, true)
            binding.errorListTextView.text = content.message
            binding.errorListTextView.visibility = View.VISIBLE
        }
    }

    private fun collectError() {
        breweryListViewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    override fun onClick(brewery: Brewery) {
        val bundle = bundleOf(BreweryFragment.BREWERY_ID to brewery.id)
        navigate(R.id.action_breweryListFragment_to_breweryFragment, bundle)
    }

    override fun onRefresh() {
        fetchBreweriesList()
    }

    override fun onDestroy() {
        super.onDestroy()
        breweryListViewModel.errorLiveData.removeObserver(errorObserver)
    }
}