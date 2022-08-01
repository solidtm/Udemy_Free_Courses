package com.solid.ufc.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        registerListeners()
        return binding.root
    }

    private fun registerListeners() {
        with(binding){
            editAcct.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
            }
            changePass.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_changePassFragment)
            }
            language.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_languageFragment)
            }
            notifications.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_notificationFragment)
            }
            closeAcct.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_closeAccountFragment)
            }

        }
    }
}