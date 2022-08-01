package com.solid.ufc.features.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.solid.ufc.R
import com.solid.ufc.data.model.explore.CourseItem
import com.solid.ufc.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private lateinit var binding: FragmentExploreBinding
    private lateinit var adapter: CoursesItemAdapter
    private lateinit var otherAdapter: OtherCoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(layoutInflater)

        setOnBoardingItems()
        return binding.root
    }

    private fun setOnBoardingItems() {
        val courseItems = listOf(
            CourseItem(
                courseImage = R.drawable.top_rated_image,
                courseName = "Business strategies\nand growth",
                tutorName = "ELmino Dare",
                stars = " 4.7 (30K)"
            ),
            CourseItem(
                courseImage = R.drawable.top_rated_image,
                courseName = "Business strategies\n" +
                        "and growth",
                tutorName = "ELmino Dare",
                stars = " 4.7 (30K)"
            ),
            CourseItem(
                courseImage = R.drawable.top_rated_image,
                courseName = "Business strategies\n" +
                        "and growth",
                tutorName = "ELmino Dare",
                stars = " 4.7 (30K)"
            )
        )

        val otherCourses = listOf(
            CourseItem(
                courseImage = R.drawable.top_rated_image,
                courseName = "Business strategies and growth",
                tutorName = "ELmino Dare",
                stars = " 3.8 (30K)"
            ),
            CourseItem(
                courseImage = R.drawable.top_rated_image,
                courseName = "Business strategies " +
                        "and growth",
                tutorName = "ELmino Dare",
                stars = " 3.8 (30K)"
            ),
            CourseItem(
                courseImage = R.drawable.top_rated_image,
                courseName = "Business strategies " +
                        "and growth",
                tutorName = "ELmino Dare",
                stars = " 3.8 (30K)"
            )
        )

        adapter = CoursesItemAdapter()
        otherAdapter = OtherCoursesAdapter()
        adapter.setList(courseItems)
        otherAdapter.setList(otherCourses)
        binding.topRatedRv.adapter = adapter
        binding.topPicksRv.adapter = otherAdapter
        binding.alsoRv.adapter = otherAdapter
    }
}