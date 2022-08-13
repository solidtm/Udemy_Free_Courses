package com.solid.ufc.features.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentCloseAccountBinding
import com.solid.ufc.databinding.FragmentLanguageBinding

class CloseAccountFragment : Fragment() {

    private lateinit var binding: FragmentCloseAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCloseAccountBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}