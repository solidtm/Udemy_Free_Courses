package com.solid.ufc.features.signup

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.data.model.signup.InterestItem
import com.solid.ufc.databinding.FragmentInterestsBinding
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import java.lang.StringBuilder


class InterestsFragment : Fragment() {
    private lateinit var binding: FragmentInterestsBinding
    private lateinit var adapter: InterestsAdapter
    private lateinit var interests: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInterestsBinding.inflate(layoutInflater)
        interests = arrayListOf()

        setupRecyclerView()
        setUpListeners()
        return binding.root
    }

    private fun setUpListeners() {
        with(binding) {
            continueBtn.setOnClickListener {
                val sb = StringBuilder()
                for (i in 0 until interests.size) {
                   sb.append(interests[i] + " ")
                }
                SharePreference(requireContext()).setString(SharedPrefKeys.INTERESTS, sb.toString())
                SharePreference(requireContext()).setBoolean(SharedPrefKeys.INTERESTS, true)
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }
        }
    }

    private fun setupRecyclerView() {
        val interestItem = listOf(
            InterestItem(
                image = R.drawable.dummy_img,
                desc = "Business"
            ),
            InterestItem(
                image = R.drawable.design,
                desc = "Design"
            ), InterestItem(
                image = R.drawable.development,
                desc = "Development"
            ),
            InterestItem(
                image = R.drawable.fin_and_acct,
                desc = "Finance and Accounting"
            ), InterestItem(
                image = R.drawable.health,
                desc = "Health and Fitness"
            ), InterestItem(
                image = R.drawable.it,
                desc = "IT and Software"
            ), InterestItem(
                image = R.drawable.lifestlye,
                desc = "Lifestyle"
            ), InterestItem(
                image = R.drawable.marketing,
                desc = "Marketing"
            ), InterestItem(
                image = R.drawable.music,
                desc = "Music"
            ), InterestItem(
                image = R.drawable.office_prod,
                desc = "Office Productivity"
            ), InterestItem(
                image = R.drawable.personal_dev,
                desc = "Personal Development"
            ), InterestItem(
                image = R.drawable.photo,
                desc = "Photography and Video"
            ), InterestItem(
                image = R.drawable.teaching,
                desc = "Teaching and Academics"
            ), InterestItem(
                image = R.drawable.udemy,
                desc = "Udemy Free Resources"
            ), InterestItem(
                image = R.drawable.vodafone,
                desc = "Vodafone"
            )
        )

        initRecyclerView(interestItem)
    }

    private fun initRecyclerView(interestItem: List<InterestItem>){
        adapter = InterestsAdapter(
            { item -> itemClicked(item) },
            { item -> itemUnClicked(item) }, requireContext())
        adapter.setList(interestItem)
        with(binding) {
            recyclerview.adapter = adapter
            recyclerview.layoutManager = GridLayoutManager(requireContext(), 3)
            val itemDecoration = ItemOffsetDecoration(context!!,
                R.dimen.margin_4
            )
            recyclerview.addItemDecoration(itemDecoration)
        }
    }

    private fun itemClicked(category: String): Boolean {
        return if (!interests.contains(category) && interests.size <= 15) {
            interests.add(category)
            println("Added: $category")
            true
        } else if (interests.contains(category)) {
            itemUnClicked(category)
            Snackbar.make(requireView(), "Already added $category", Snackbar.LENGTH_SHORT).show()
            false
        } else if (interests.size >= 15){
            Snackbar.make(requireView(), "Cannot select more areas of interests", Snackbar.LENGTH_SHORT)
                .show()
            false
        }else{
            true
        }
    }

    private fun itemUnClicked(category: String) {
        for (i in 0 until interests.size) {
            if (interests[i] == category) {
                interests.removeAt(i)
                println("Removed: $category")
                break
            }
        }
    }


    inner class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {
        constructor(context: Context, @DimenRes itemOffsetId: Int) : this(
            context.resources.getDimensionPixelSize(itemOffsetId)
        )

       override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
        }
    }
}