package com.solid.ufc.features.explore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.data.model.explore.CourseItem
import com.solid.ufc.data.model.explore.CourseListRequest
import com.solid.ufc.data.model.explore.Result
import com.solid.ufc.databinding.FragmentExploreBinding
import com.solid.ufc.util.ProgressLoader
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import com.solid.ufc.util.showDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    @Inject
    lateinit var progressLoader: ProgressLoader
    private lateinit var binding: FragmentExploreBinding
    private lateinit var adapter: CoursesItemAdapter
    private lateinit var otherAdapter: OtherCoursesAdapter
    private lateinit var courseItems: ArrayList<CourseItem>
    private lateinit var otherCourses: ArrayList<CourseItem>
    private val exploreViewModel: ExploreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(layoutInflater)

        setCourseItems()
        registerListeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        showVerifyDialog()

    }

    private fun registerListeners(){
        binding.seeAllRated.setOnClickListener {
           SharePreference(requireContext()).setString(SharedPrefKeys.SEE_ALL, "Top Rated")
           findNavController().navigate(R.id.action_navigation_explore_to_seeAllFragment)
        }
        binding.seeAllAlso.setOnClickListener {
            SharePreference(requireContext()).setString(SharedPrefKeys.SEE_ALL, "Our top Picks for you")
           findNavController().navigate(R.id.action_navigation_explore_to_seeAllFragment)
        }
        binding.seeAllPicks.setOnClickListener {
            SharePreference(requireContext()).setString(SharedPrefKeys.SEE_ALL, "Courses you may like")
           findNavController().navigate(R.id.action_navigation_explore_to_seeAllFragment)
        }
    }

    private fun setCourseItems() {
        getCourses()
        getOtherCourses()
    }

    private fun showVerifyDialog(){
      val isVerified =  SharePreference(requireContext()).getBoolean(SharedPrefKeys.IS_VERIFIED)
      val firstName =  SharePreference(requireContext()).getString(SharedPrefKeys.FIRST_NAME)
      binding.welcome.text = "Welcome $firstName!"

        if (!isVerified){
            context?.showDialog(
                title = "Notice",
                message = "Please verify your email address"
            ){
                findNavController().navigate(R.id.action_navigation_explore_to_navigation_profile)
            }
        }
    }

    private fun getCourses(){
        val courseRequest = CourseListRequest(
            page = 1,
            pageSize = 5
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
                        displayCourses(it.courseListResponse?.results)
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

    private fun getOtherCourses(){
        val courseRequest = CourseListRequest(
            page = 1,
            pageSize = 5
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

    private fun displayCourses(courses: List<Result>?){
        courseItems = arrayListOf()

        courses?.forEach {
           courseItems.add(
               CourseItem(
                   courseName = it.title,
                   courseImage = it.image125H,
                   tutorName = it.visibleInstructors[0].displayName,
                   stars = it.price,
                   courseLink = it.url
               )
           )
        }

        adapter = CoursesItemAdapter(
            { item -> itemClicked(item) },
            requireContext())
        adapter.setList(courseItems)
        binding.topRatedRv.adapter = adapter
    }

    private fun itemClicked(url: String) {
        val urlComp = "https://www.udemy.com$url"
        println(urlComp)
        val openLinkIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlComp))
        context?.startActivity(openLinkIntent)
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

        otherAdapter = OtherCoursesAdapter(context = requireContext(), checkListener = { item -> itemClicked(item) })
        otherAdapter.setList(otherCourses)
        binding.topPicksRv.adapter = otherAdapter
        binding.alsoRv.adapter = otherAdapter
    }
}