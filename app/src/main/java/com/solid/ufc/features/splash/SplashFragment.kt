package com.solid.ufc.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentSplashBinding
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSplashBinding.inflate(layoutInflater)

        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginState()
        }, 2000)

        return binding.root
    }

    private fun checkLoginState() {
        if (userIsLoggedIn()){ //go to home screen
            startActivity(Intent(context, HomeActivity::class.java))
            activity?.finish()
        }else {
            if (onBoardingIsFinished()){ //go to login page
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }else{ //onboard users
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        }
    }

    private fun onBoardingIsFinished(): Boolean {
       return SharePreference(requireContext()).getBoolean(SharedPrefKeys.ONBOARDED, false)
    }

    //CHECK IF user is logged in via email and password or google
    // and go to the main screen if not go to login

    private fun userIsLoggedIn(): Boolean{
       return loggedInViaEmail() || loggedInViaGoogle()
    }

    private fun loggedInViaEmail(): Boolean {
       return SharePreference(requireContext()).getBoolean(SharedPrefKeys.EMAIL_LOGIN, false)
    }

    private fun loggedInViaGoogle(): Boolean {
       return SharePreference(requireContext()).getBoolean(SharedPrefKeys.GOOGLE_LOGIN, false)
    }



//    override fun onStart() {
//        super.onStart()
//        checkedLoggedInState()  //same func from login
//    }

}