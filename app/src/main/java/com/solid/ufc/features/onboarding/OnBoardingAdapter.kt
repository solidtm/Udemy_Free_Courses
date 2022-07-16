package com.solid.ufc.features.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solid.ufc.R
import com.solid.ufc.data.model.onboarding.OnBoardingItem
import com.solid.ufc.databinding.OnboardingItemsBinding

class OnBoardingItemsAdapter
    : RecyclerView.Adapter<OnBoardingViewHolder>(){

    private val onBoardingItemList = ArrayList<OnBoardingItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : OnboardingItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.onboarding_items, parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingItemList[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItemList.size
    }

    fun setList(itemList: List<OnBoardingItem>){
        onBoardingItemList.addAll(itemList)
    }
}


class OnBoardingViewHolder(binding : OnboardingItemsBinding) : RecyclerView.ViewHolder(binding.root){
    private val imageOnBoarding = binding.imageOnBoarding
    private val textTitle = binding.titleText
    private val textDescription = binding.bodyText

    fun bind(onBoardingItem: OnBoardingItem){
        imageOnBoarding.setImageResource(onBoardingItem.image)
        textTitle.text = onBoardingItem.title
        textDescription.text = onBoardingItem.description
    }
}