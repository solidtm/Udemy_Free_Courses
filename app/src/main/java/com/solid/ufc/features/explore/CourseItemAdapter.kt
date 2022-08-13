package com.solid.ufc.features.explore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.solid.ufc.R
import com.solid.ufc.data.model.explore.CourseItem
import com.solid.ufc.databinding.TopRatedCourseItemBinding

class CoursesItemAdapter(
    private val checkListener: (String) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<CourseItemViewHolder>() {

    private val courseItemList = ArrayList<CourseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TopRatedCourseItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.top_rated_course_item, parent, false)
        return CourseItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseItemViewHolder, position: Int) {
        holder.bind(courseItemList[position], context, checkListener)
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun setList(itemList: List<CourseItem>) {
        courseItemList.addAll(itemList)
    }
}

class CourseItemViewHolder(val binding: TopRatedCourseItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val courseImage = binding.imageView
    private val courseName = binding.courseName
    private val tutorName = binding.tutorName
    private val stars = binding.stars

    fun bind(
        courseItem: CourseItem,
             context: Context,
             checkListener: (String) -> Unit
    ) {
        loadProfileImage(context, courseItem.courseImage, courseImage)
        courseName.text = courseItem.courseName
        tutorName.text = courseItem.tutorName
        stars.text = courseItem.stars

        binding.cardView.setOnClickListener {
            checkListener(courseItem.courseLink)
        }
    }

    private fun loadProfileImage(context: Context, imageUrl: String, profileImage: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .into(profileImage)
    }
}