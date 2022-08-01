package com.solid.ufc.features.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentLoginBinding.inflate(layoutInflater)

        setUpListeners()
        return binding.root
    }

    private fun setUpListeners(){
        with(binding){
            buttonSignIn.setOnClickListener {
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            }

            signUpText.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }

            forgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_resetPassFragment)
            }
        }
    }

}