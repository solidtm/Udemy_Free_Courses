package com.solid.ufc.features.profile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.solid.ufc.R
import com.solid.ufc.StarterActivity
import com.solid.ufc.databinding.FragmentProfileBinding
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import kotlin.math.sign

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        registerListeners()
        return binding.root
    }

    private fun registerListeners() {
        with(binding){
            editAcct.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
            }
            changePass.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_changePassFragment)
            }
            language.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_languageFragment)
            }
            notifications.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_notificationFragment)
            }
            closeAcct.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_profile_to_closeAccountFragment)
            }
            signOut.setOnClickListener {
                signOut()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
//        setupUI()
    }

    private fun setupUI(){
        val firstName = SharePreference(requireContext()).getString(SharedPrefKeys.FIRST_NAME)
        val lastName = SharePreference(requireContext()).getString(SharedPrefKeys.LAST_NAME)
        val email = SharePreference(requireContext()).getString(SharedPrefKeys.EMAIL)
        val imageUrl = SharePreference(requireContext()).getString(SharedPrefKeys.IMG_URL)

        binding.labelName.text = "$firstName  $lastName"
        binding.labelEmail.text = email
        loadProfileImage(imageUrl)
    }

    private fun loadProfileImage(imageUrl: String){
        Glide.with(requireContext())
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.profileImage)
    }

    private fun signOut(){
        AlertDialog.Builder(requireContext())
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                auth.signOut()
                SharePreference(requireContext()).clear(SharedPrefKeys.TOKEN)
                SharePreference(requireContext()).clear(SharedPrefKeys.FIRST_NAME)
                SharePreference(requireContext()).clear(SharedPrefKeys.LAST_NAME)
                SharePreference(requireContext()).clear(SharedPrefKeys.IMG_URL)
                startActivity(Intent(requireContext(), StarterActivity::class.java))
                activity?.finish()
            }
            .setNegativeButton("No", null)
            .show()
    }
}