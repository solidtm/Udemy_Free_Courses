package com.solid.ufc.features.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solid.ufc.R
import com.solid.ufc.data.model.explore.CourseItem
import com.solid.ufc.databinding.CourseItemBinding
import com.solid.ufc.databinding.TopRatedCourseItemBinding

class OtherCoursesAdapter : RecyclerView.Adapter<OtherCoursesViewHolder>(){

    private val courseItemList = ArrayList<CourseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherCoursesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : CourseItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.course_item, parent, false)
        return OtherCoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherCoursesViewHolder, position: Int) {
        holder.bind(courseItemList[position])
    }

    override fun getItemCount(): Int {
        return courseItemList.size
    }

    fun setList(itemList: List<CourseItem>){
        courseItemList.addAll(itemList)
    }
}


class OtherCoursesViewHolder(val binding : CourseItemBinding) : RecyclerView.ViewHolder(binding.root){
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