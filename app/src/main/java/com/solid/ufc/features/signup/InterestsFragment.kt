package com.solid.ufc.features.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.data.model.signup.InterestItem
import com.solid.ufc.databinding.FragmentInterestsBinding

class InterestsFragment : Fragment() {
    private lateinit var binding: FragmentInterestsBinding
    private lateinit var adapter: InterestsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentInterestsBinding.inflate(layoutInflater)

        setOnBoardingItems()
        setUpListeners()
        return binding.root
    }

    private fun setUpListeners(){
        with(binding){
            continueBtn.setOnClickListener {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }
    }

    private fun setOnBoardingItems() {
        val interestItem = listOf(
            InterestItem(
                image = R.drawable.dummy_img,
                desc = "Business"
            ),
            InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            ),
            InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            ),InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            ),InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            ),InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            ),InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            ),InterestItem(
                image = R.drawable.dummy_img,
                desc = "Business"
            ),InterestItem(
                image = R.drawable.dummy_img,
                desc = "Design"
            )
        )

        adapter = InterestsAdapter()
        adapter.setList(interestItem)
        with(binding){
            recyclerview.adapter = adapter
            recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
        }
    }
}