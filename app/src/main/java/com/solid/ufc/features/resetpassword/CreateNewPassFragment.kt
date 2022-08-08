package com.solid.ufc.features.resetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentCreateNewPassBinding
import com.solid.ufc.databinding.FragmentVerifyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNewPassFragment : Fragment() {

    private lateinit var binding: FragmentCreateNewPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreateNewPassBinding.inflate(layoutInflater)

        registerListeners()
        return binding.root
    }

    private fun registerListeners() {
        with(binding){
            buttonChangePass.setOnClickListener {
                findNavController().navigate(R.id.action_createNewPassFragment_to_loginFragment)
            }
        }
    }
}