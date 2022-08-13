package com.solid.ufc.features.resetpassword

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentResetPassBinding
import com.solid.ufc.util.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPassFragment : Fragment() {

    private lateinit var binding: FragmentResetPassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPassBinding.inflate(layoutInflater)

        registerListeners()
        return binding.root
    }

    private fun validateEmail(value: String): Boolean {

        return when {
            value.isEmpty() -> {
                with(binding) {
                    emailLayout.error = "Field cannot be empty"
                    emailLayout.errorIconDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                    println("$value: email is empty")
                }
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> {
                with(binding) {
                    emailLayout.error = "Invalid email"
                    emailLayout.errorIconDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                    println("$value: didn't match pattern")
                }
                false
            }
            else -> {
                with(binding) {
                    emailLayout.error = null
                    emailLayout.isErrorEnabled = false
                }
                true
            }
        }
    }

    private fun registerListeners() {
        with(binding) {
//            val email = emailLayout.editText?.text.toString().trim()
            val email = "richard@gmail.com"
            buttonSendCode.setOnClickListener {
                if (validateEmail(email)) {
                    //show dialog
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                context?.showDialog(
                                    title = "Success!",
                                    message = "Reset password instructions sent"
                                ) {
                                    findNavController().navigate(R.id.action_resetPassFragment_to_loginFragment)
                                }
                            }else{
                                context?.showDialog(
                                    title = "Error!",
                                    message = it.exception!!.message.toString()
                                ) {
                                    findNavController().navigateUp()
                                }
                            }
                        }
                }
            }

            signUpText.setOnClickListener {
                findNavController().navigate(R.id.action_resetPassFragment_to_signUpFragment)
            }
        }
    }

}