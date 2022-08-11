package com.solid.ufc.features.signup

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.solid.ufc.R
import com.solid.ufc.data.model.signup.InterestItem
import com.solid.ufc.databinding.InterestsItemBinding

class InterestsAdapter(
    private val checkListener: (String) -> Boolean,
    private val uncheckListener: (String) -> Unit,
    private val context : Context
) : RecyclerView.Adapter<InterestsViewHolder>() {

    private val interestItemList = ArrayList<InterestItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: InterestsItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.interests_item, parent, false)
        return InterestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        holder.bind(interestItemList[position], checkListener, uncheckListener, context)
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
    private val card = binding.cardView
    private val layout = binding.layout

    fun bind(
        interestItem: InterestItem,
        checkListener: (String) -> Boolean,
        uncheckListener: (String) -> Unit,
        context: Context
    ) {
        image.setImageResource(interestItem.image)
        desc.text = interestItem.desc
        tick.visibility = View.GONE

        card.setOnClickListener {
            if (!tick.isVisible) {
                tick.visibility = View.VISIBLE
                desc.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                layout.background = ContextCompat.getDrawable(context, R.drawable.items_checked_bg)
                checkListener(interestItem.desc)
            } else {
                tick.visibility = View.GONE
                desc.setTextColor(ContextCompat.getColor(context, R.color.white))
                layout.background = ContextCompat.getDrawable(context, R.drawable.items_bg)
                uncheckListener(interestItem.desc)
            }
        }
    }
}