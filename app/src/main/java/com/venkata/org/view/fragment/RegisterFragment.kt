package com.venkata.org.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.venkata.org.R
import com.venkata.org.databinding.FragmentRegisterBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.registration.RegistrationRequest
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class RegisterFragment:Fragment() {

    lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() {
        initViewModel()
        setupObservers()

        binding.btnRegister.setOnClickListener {
            addUserRegistration()
        }

        binding.txtHaveAccount.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.myContainer, LoginFragment())
                .commit()
        }

    }

    private fun addUserRegistration() {
        with(binding) {
            val name = edtFullName.text.toString()
            val emailId = edtEmailId.text.toString()
            val mobileNumber = edtMobileNumber.text.toString()
            val password = edtPassword.text.toString()

            val registrationRequest = RegistrationRequest(name, mobileNumber, emailId, password)
            viewModel.addUserRegistration(registrationRequest)
        }
    }

    private fun setupObservers() {
        viewModel.apiStateUserRegistration.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {
                    Toast.makeText(context, "${it.data.message}", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.myContainer, LoginFragment()).addToBackStack("Register")
                        .commit()
                }
            }
        }
    }

    private fun initViewModel() {
        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }


}