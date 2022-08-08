package com.solid.ufc.features.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.data.model.onboarding.OnBoardingItem
import com.solid.ufc.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_onboarding),
    ViewPager2.PageTransformer {
    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var adapter: OnBoardingItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(layoutInflater)

        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.setPageTransformer(this)

        setOnBoardingItems()
        configureNextButton()
        configureBackButton()
        configureSkipText()

        return binding.root
    }

    //this function sets the items of the viewPager
    private fun setOnBoardingItems() {
        val onBoardingItems = listOf(
            OnBoardingItem(
                image = R.drawable.ic_onboard1,
                title = "Get Free Udemy Courses",
                description = "Save the hassle of searching, have access\n" +
                        "to thousands of free Udemy courses \n" +
                        "based on their categories"
            ),
            OnBoardingItem(
                image = R.drawable.ic_onboard2,
                title = "Get Course Notifications",
                description = "Receive notifications on new and existing\n free courses based on your selected\n interests."
            ),
            OnBoardingItem(
                image = R.drawable.ic_onboard3,
                title = "Explore Top-rated Courses",
                description = "Explore top rated courses and courses on\n demand, selected just for \n" +
                        "you."
            )
        )

        adapter = OnBoardingItemsAdapter()
        adapter.setList(onBoardingItems)
        binding.viewPager.adapter = adapter
    }


    //this function listens to next button
    private fun configureNextButton() {
        binding.onboardNextBtn.setOnClickListener {
            when (binding.viewPager.currentItem) {
                0 -> {
                    binding.viewPager.currentItem = 1
                    binding.onboardProgressBar.progress = 50
                    binding.onboardBackBtn.isVisible = true
                }
                1 -> {
                    binding.viewPager.currentItem = 2
                    binding.onboardProgressBar.progress = 75
                }
                else -> {
                    findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
                }
            }
        }
    }

    //this function listens to back button
    private fun configureBackButton() {
        binding.onboardBackBtn.setOnClickListener {
            when (binding.viewPager.currentItem) {
                1 -> {
                    binding.onboardBackBtn.isVisible = false
                    binding.viewPager.currentItem = 0
                    binding.onboardProgressBar.progress = 25
                }
                2 -> {
                    binding.viewPager.currentItem = 1
                    binding.onboardProgressBar.progress = 50
                }else -> {}
            }
        }
    }

    private fun configureSkipText() {
        binding.textSkip.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

    //pop animation for view pager
    override fun transformPage(page: View, position: Float) {
        page.translationX = -position * page.width

        if (abs(position) < 0.5) {
            page.visibility = View.VISIBLE
            page.scaleX = 1 - abs(position)
            page.scaleY = 1 - abs(position)
        } else if (abs(position) > 0.5) {
            page.visibility = View.GONE
        }
    }
}