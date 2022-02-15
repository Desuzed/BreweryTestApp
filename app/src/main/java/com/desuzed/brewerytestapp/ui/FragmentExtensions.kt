package com.desuzed.brewerytestapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun Fragment.navigate(directions: Int, bundle: Bundle? = null) {
    val controller = findNavController()
    val currentDestination =
        (controller.currentDestination as? FragmentNavigator.Destination)?.className
            ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className
    if (currentDestination == this.javaClass.name) {
        if (bundle == null) {
            controller.navigate(directions)
        } else {
            controller.navigate(directions, bundle)
        }
    }
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun toggleRefresh(swipeRefreshLayout: SwipeRefreshLayout, state: Boolean) {
    swipeRefreshLayout.isRefreshing = state
}

fun toggleButtonVisibility (button: Button, state : Boolean){
    when (state){
        true -> button.visibility = View.VISIBLE
        false -> button.visibility = View.GONE
    }
}