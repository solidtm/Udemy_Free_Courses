package com.solid.ufc.features.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentEditProfileBinding
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import com.solid.ufc.util.showDialog

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentEditProfileBinding.inflate(layoutInflater)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveText.setOnClickListener {
            updateProfile()

            context?.showDialog(
                title = "Success!",
                message = "Changes saved successfully."
            ){
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    private fun updateProfile(){
        with(binding){
           SharePreference(requireContext()).setString(SharedPrefKeys.FIRST_NAME, firstNameLayout.editText?.text.toString().trim())
           SharePreference(requireContext()).setString(SharedPrefKeys.LAST_NAME, lastNameLayout.editText?.text.toString().trim())
           SharePreference(requireContext()).setString(SharedPrefKeys.EMAIL, emailLayout.editText?.text.toString().trim())
        }
    }

}