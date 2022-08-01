package com.solid.ufc.features.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentResetPassBinding

class ResetPassFragment : Fragment() {

    private lateinit var binding: FragmentResetPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentResetPassBinding.inflate(layoutInflater)

        registerListeners()
        return binding.root
    }

    private fun registerListeners(){
        with(binding){
            buttonSendCode.setOnClickListener {
                findNavController().navigate(R.id.action_resetPassFragment_to_verifyFragment)
            }

            signUpText.setOnClickListener {
                findNavController().navigate(R.id.action_resetPassFragment_to_signUpFragment)
            }
        }
    }

}