package com.app.mobileapptask.ui.fragment.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.mobileapptask.constant.AppConstant
import com.app.mobileapptask.databinding.LoginFragmentBinding
import com.app.mobileapptask.utils.Status
import com.app.mobileapptask.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signIn.setOnClickListener {
            if (isValidFields()) {
                viewModel.loginUser(
                    emailAddress = binding.email.text.toString(),
                    password = binding.password.text.toString()
                )
            }
        }

        binding.signUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        viewModel.status.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> moveToWelcomeFragment()
                Status.ERROR -> showErrorMessage(it.data)
                Status.LOADING -> showLoader()
            }
        })
    }

    private fun showLoader() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showErrorMessage(message: String?) {
        hideLoader()
        message?.let {
            toast(message)
        }
    }

    private fun hideLoader() {
        binding.progressBar.visibility = View.GONE
    }

    private fun moveToWelcomeFragment() {
        hideLoader()
        AppConstant.saveUserEmail(requireContext(), emailAddress = binding.email.text.toString())
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

    private fun isValidFields(): Boolean {

        if (binding.email.text.toString().isEmpty() ||
            !Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()
        ) {
            binding.emailTextInput.error = "Invalid Email Address"
            return false
        }

        if (binding.password.text.toString().isEmpty()) {
            binding.passwordTextInput.error = "Invalid Password"
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}