package com.solid.ufc.features.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentSeeAllBinding

class SeeAllFragment : Fragment() {

  private lateinit var binding: FragmentSeeAllBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(layoutInflater)

        return binding.root
    }
}