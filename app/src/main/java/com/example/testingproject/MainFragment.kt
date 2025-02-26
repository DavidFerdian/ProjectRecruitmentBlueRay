package com.example.testingproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.testingproject.R
import com.example.testingproject.databinding.FragmentMainBinding
import com.example.testingproject.sharedPreference.LoginStatusPreference

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var navController: NavController

    lateinit var loggedStatusPreference: LoginStatusPreference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        navController = this.findNavController()

        loggedStatusPreference =
            LoginStatusPreference(
                activity as AppCompatActivity
            )
        navigateToLogin()
        //validateLoggedStatus()
        return binding.root
    }


    private fun navigateToLogin() {
        navController.addOnDestinationChangedListener { nc: NavController, _: NavDestination, _ ->
            nc.graph.startDestination = R.id.loginFragment
        }

        val options = NavOptions.Builder()
            .setPopUpTo(R.id.mainFragment, true)
            .build()
        navController.navigate(R.id.loginFragment, null, options)
    }


}