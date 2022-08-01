package com.solid.ufc.features.resetpassword

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentResetPassBinding
import com.solid.ufc.databinding.FragmentVerifyBinding

class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentVerifyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentVerifyBinding.inflate(layoutInflater)

        registerListeners()
        return binding.root
    }

    private fun registerListeners() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_verifyFragment_to_createNewPassFragment)
        }, 5000)
    }
}