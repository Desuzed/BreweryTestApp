package com.desuzed.brewerytestapp.ui.brewery_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.desuzed.brewerytestapp.App
import com.desuzed.brewerytestapp.R
import com.desuzed.brewerytestapp.databinding.FragmentBreweryListBinding
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.ui.ViewModelFactory
import com.desuzed.brewerytestapp.ui.adapter.BreweryAdapter
import com.desuzed.brewerytestapp.ui.adapter.OnBreweryItemClickListener
import com.desuzed.brewerytestapp.ui.navigate

class BreweryListFragment : Fragment(), OnBreweryItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: FragmentBreweryListBinding
    private val breweryAdapter = BreweryAdapter(this)
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
        binding = FragmentBreweryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fetchBreweriesList()
        observe()
    }

    private fun fetchBreweriesList() {
        breweryViewModel.fetchBreweriesList()
        toggleRefresh(true)
    }

    private fun setupViews() {
        binding.breweryRecyclerView.adapter = breweryAdapter
        binding.breweryListSwipeRefresh.setOnRefreshListener(this)
    }

    private fun observe() {
        breweryViewModel.breweriesListLiveData.observe(viewLifecycleOwner, {
            breweryAdapter.submitList(it)
            toggleRefresh(false)
        })
    }

    private fun toggleRefresh(state: Boolean) {
        binding.breweryListSwipeRefresh.isRefreshing = state
    }

    override fun onClick(brewery: Brewery) {
        val bundle = bundleOf(BreweryFragment.BREWERY_ID to brewery.id)
        navigate(R.id.action_breweryListFragment_to_breweryFragment, bundle)
    }

    override fun onRefresh() {
        fetchBreweriesList()
    }

}