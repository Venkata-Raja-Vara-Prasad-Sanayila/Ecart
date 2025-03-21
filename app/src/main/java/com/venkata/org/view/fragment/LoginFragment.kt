package com.venkata.org.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.venkata.org.R
import com.venkata.org.databinding.FragmentLoginBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.login.LoginRequest
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.model.sharedPreference.SharedPreferenceManager
import com.venkata.org.view.MainActivity
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class LoginFragment: Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        initialiseView()
        return binding.root
    }

    private fun initialiseView() {
        initViewModel()
        setupObservers()
        binding.btnLogin.setOnClickListener {
            loginUserVerify()
        }

        binding.txtNewAccount.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.myContainer, RegisterFragment()).addToBackStack("LoginFragment")
                .commit()
        }
    }

    private fun loginUserVerify() {
        with(binding) {
            val emailId = edtLoginEmailId.text.toString()
            val password = edtLoginPassword.text.toString()

            val loginRequest = LoginRequest(emailId, password)
            viewModel.getUserLogin(loginRequest)
        }
    }

    private fun setupObservers() {
        viewModel.apiStateLoginUser.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {
                    SharedPreferenceManager.saveBoolean(SharedPreferenceManager.KEY_IS_LOGGED_IN, true)
                    it.data.user?.let {
                        it1 -> SharedPreferenceManager.saveUser(SharedPreferenceManager.KEY_USER, it1)
                    }
                    Toast.makeText(context, "${it.data.message}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                    requireActivity().finish()
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