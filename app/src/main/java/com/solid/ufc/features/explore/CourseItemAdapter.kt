package com.solid.ufc.features.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solid.ufc.R
import com.solid.ufc.data.model.explore.CourseItem
import com.solid.ufc.databinding.TopRatedCourseItemBinding

class CoursesItemAdapter : RecyclerView.Adapter<CourseItemViewHolder>(){

    private val courseItemList = ArrayList<CourseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : TopRatedCourseItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.top_rated_course_item, parent, false)
        return CourseItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseItemViewHolder, position: Int) {
        holder.bind(courseItemList[position])
    }

    override fun getItemCount(): Int {
        return courseItemList.size
    }

    fun setList(itemList: List<CourseItem>){
        courseItemList.addAll(itemList)
    }
}

class CourseItemViewHolder(val binding : TopRatedCourseItemBinding) : RecyclerView.ViewHolder(binding.root){
    private val courseImage = binding.imageView
    private val courseName = binding.courseName
    private val tutorName = binding.tutorName
    private val stars = binding.stars
    fun bind(courseItem: CourseItem){
        courseImage.setImageResource(courseItem.courseImage)
        courseName.text = courseItem.courseName
        tutorName.text = courseItem.tutorName
        stars.text = courseItem.stars
    }
}