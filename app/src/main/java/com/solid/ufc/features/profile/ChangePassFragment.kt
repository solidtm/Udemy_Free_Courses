package com.solid.ufc.features.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentChangePassBinding

class ChangePassFragment : Fragment() {

    private lateinit var binding: FragmentChangePassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentChangePassBinding.inflate(layoutInflater)

        return binding.root
    }
}