package com.solid.ufc.features.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentOnboardingBinding
import com.solid.ufc.databinding.FragmentSignUpBinding
import com.solid.ufc.util.ProgressLoader
import com.solid.ufc.util.SharePreference
import com.solid.ufc.util.SharedPrefKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    lateinit var auth : FirebaseAuth
    private lateinit var  firstName: String
    private lateinit var lastName: String
    private lateinit var email: String

    @Inject
    lateinit var progressLoader: ProgressLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentSignUpBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()


        setUpListeners()
        return binding.root
    }


    private fun registerUser(){
        val password = binding.passLayout.editText?.text.toString().trim()
        with(binding){
            email = binding.emailLayout.editText?.text.toString().trim()
            firstName = firstNameLayout.editText?.text.toString().trim()
            lastName = lastNameLayout.editText?.text.toString().trim()
        }

        if (inputsValid(email, password)){
            progressLoader.show("Please wait...", false)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState(email)
                    }
                }catch (ex: Exception){
                    withContext(Dispatchers.Main){
                        progressLoader.hide()
                        Toast.makeText(context, "${ex.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    private fun checkLoggedInState(email: String) {
        if (auth.currentUser == null){
            progressLoader.hide()
            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }else{
            progressLoader.hide()
            SharePreference(requireContext()).setString(SharedPrefKeys.FIRST_NAME, firstName)
            SharePreference(requireContext()).setString(SharedPrefKeys.LAST_NAME, lastName)
            SharePreference(requireContext()).setString(SharedPrefKeys.EMAIL, email)
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun inputsValid(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun setUpListeners(){
        with(binding){
            buttonSignUp.setOnClickListener {
               registerUser()
            }

            signInText.setOnClickListener {
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }

            backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}