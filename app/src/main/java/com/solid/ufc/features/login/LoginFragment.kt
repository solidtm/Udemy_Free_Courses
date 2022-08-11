package com.solid.ufc.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.solid.ufc.HomeActivity
import com.solid.ufc.R
import com.solid.ufc.databinding.FragmentLoginBinding
import com.solid.ufc.util.ProgressLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val REQUEST_CODE_SIGN_IN = 0

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var progressLoader: ProgressLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        setUpListeners()
        return binding.root
    }

    private fun setUpListeners() {
        with(binding) {
            buttonSignIn.setOnClickListener {
                loginUserWithEmailAndPass()
            }

            signUpText.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }

            forgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_resetPassFragment)
            }

            googleSignUpBtn.setOnClickListener {
                loginUserWithGoogle()
            }
        }
    }

    private fun validateEmail(value: String): Boolean {

        return when {
            value.isEmpty() -> {
                with(binding) {
                    emailLayout.error = "Field cannot be empty"
                    emailLayout.errorIconDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                }
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> {
                with(binding) {
                    emailLayout.error = "Invalid email"
                    emailLayout.errorIconDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                }
                false
            }
            else -> {
                with(binding){
                    emailLayout.error = null
                    emailLayout.isErrorEnabled = false
                }
                true
            }
        }
    }

    private fun validatePassword(value: String, layout: TextInputLayout): Boolean {
        return when {
            value.isEmpty() -> {
                layout.error = "Field cannot be empty"
                layout.errorIconDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                false
            }
            value.length < 8 -> {
                layout.error = "Password must be up to 8 characters"
                layout.errorIconDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_error)
                false
            }
            else -> {
                layout.error = null
                layout.isErrorEnabled = false
                true
            }
        }
    }

    private fun inputsValid(email: String, password: String): Boolean {
        return validateEmail(email) && validatePassword(password, binding.passLayout)
    }

    private fun loginUserWithEmailAndPass() {
        val email = binding.emailLayout.editText?.text.toString().trim()
        val password = binding.passLayout.editText?.text.toString().trim()

        if (inputsValid(email, password)) {
            progressLoader.show("Logging in...", false)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        checkLoggedInState()
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        progressLoader.hide()
                        Toast.makeText(context, "${ex.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(context, "Enter username or password", Toast.LENGTH_SHORT).show()
        }
    }


    private fun checkLoggedInState() {
        if (auth.currentUser == null) {
            progressLoader.hide()
            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
        } else {
            progressLoader.hide()
            findNavController().navigate(R.id.action_loginFragment_to_interestsFragment)
//            startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    private fun loginUserWithGoogle() {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.web_client_id))
            .requestEmail()
            .requestProfile()
            .build()

        val signInClient = GoogleSignIn.getClient(requireActivity(), options)
        signInClient.signInIntent.also {
            startActivityForResult(it, REQUEST_CODE_SIGN_IN)
        }
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {

            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                googleAuthForFirebase(it)
            }
        }
    }

    private fun googleAuthForFirebase(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials).await()
                withContext(Dispatchers.Main) {
                    startActivity(Intent(context, HomeActivity::class.java))
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "${ex.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}