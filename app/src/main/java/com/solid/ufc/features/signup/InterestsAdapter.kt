package com.solid.ufc.features.signup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solid.ufc.R
import com.solid.ufc.data.model.signup.InterestItem
import com.solid.ufc.databinding.InterestsItemBinding

class InterestsAdapter
    : RecyclerView.Adapter<InterestsViewHolder>() {

    private val interestItemList = ArrayList<InterestItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: InterestsItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.interests_item, parent, false)
        return InterestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        holder.bind(interestItemList[position])
    }

    override fun getItemCount(): Int {
        return interestItemList.size
    }

    fun setList(itemList: List<InterestItem>) {
        interestItemList.addAll(itemList)
    }
}


class InterestsViewHolder(binding: InterestsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    private val image = binding.imageView
    private val tick = binding.tickImage
    private val desc = binding.desc

    fun bind(interestItem: InterestItem) {
        image.setImageResource(interestItem.image)
        desc.text = interestItem.desc
        tick.visibility = View.GONE
    }
}