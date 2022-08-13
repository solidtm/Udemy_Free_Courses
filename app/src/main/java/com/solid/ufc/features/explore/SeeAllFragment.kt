package com.solid.ufc.features.explore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.solid.ufc.R
import com.solid.ufc.data.model.explore.CourseItem
import com.solid.ufc.data.model.explore.CourseListRequest
import com.solid.ufc.data.model.explore.Result
import com.solid.ufc.databinding.FragmentSeeAllBinding
import com.solid.ufc.util.ProgressLoader
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeeAllFragment : Fragment() {

  private lateinit var binding: FragmentSeeAllBinding
    @Inject
    lateinit var progressLoader: ProgressLoader
    private lateinit var otherAdapter: OtherCoursesAdapter
    private val exploreViewModel: ExploreViewModel by viewModels()
    private lateinit var otherCourses: ArrayList<CourseItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(layoutInflater)
        val seeAllText =  SharePreference(requireContext()).getString(SharedPrefKeys.SEE_ALL)
        binding.toolText.text = seeAllText

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getFullCourses()
    }

    private fun getFullCourses(){
        val courseRequest = CourseListRequest(
            page = 1,
            pageSize = 200
        )

        exploreViewModel.getCourses(courseListRequest = courseRequest)

        lifecycleScope.launchWhenCreated {
            exploreViewModel.courseListChannel.collect {
                when (it) {
                    is ExploreViewState.Loading -> {
                        progressLoader.show(message = "Please wait...")
                    }
                    is ExploreViewState.Success -> {
                        progressLoader.hide()
                        println(it.courseListResponse?.results)
                        displayOtherCourses(it.courseListResponse?.results)
                    }
                    is ExploreViewState.Failure -> {
                        progressLoader.hide()
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG)
                            .show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun displayOtherCourses(courses: List<Result>?){
        otherCourses = arrayListOf()

        courses?.forEach {
            otherCourses.add(
                CourseItem(
                    courseName = it.title,
                    courseImage = it.image125H,
                    tutorName = it.visibleInstructors[0].displayName,
                    stars = it.price,
                    courseLink = it.url
                )
            )
        }

        otherAdapter = OtherCoursesAdapter(requireContext(), checkListener = { item -> itemClicked(item) })
        otherAdapter.setList(otherCourses)
        binding.alsoRv.adapter = otherAdapter
    }

    private fun itemClicked(url: String) {
        val urlComp = "https://www.udemy.com$url"
        println(urlComp)
        val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlComp))
        context?.startActivity(openLinkIntent)
    }
}