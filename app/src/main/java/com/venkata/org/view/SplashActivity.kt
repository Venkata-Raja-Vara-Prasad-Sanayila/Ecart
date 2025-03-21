package com.venkata.org.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.venkata.org.R
import com.venkata.org.databinding.ActivitySplashBinding
import com.venkata.org.view.fragment.LoginFragment
import com.venkata.org.model.sharedPreference.SharedPreferenceManager


class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
      //  val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SharedPreferenceManager.init(this)
        initView()
    }

    private fun initView() {



        val isUserLoggedIn = SharedPreferenceManager.getBoolean(SharedPreferenceManager.KEY_IS_LOGGED_IN)
        if(isUserLoggedIn){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
//            startActivity(Intent(this, LoginActivity::class.java))

            supportFragmentManager.beginTransaction()
                .add(R.id.myContainer, LoginFragment())
                .commit()
//                .addToBackStack("LoginFragment")

//            finish()
        }
    }
}
//class SplashActivity : AppCompatActivity() {
//    lateinit var binding: ActivitySplashBinding
//    lateinit var viewModel: MainViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        val splash = installSplashScreen()
//        super.onCreate(savedInstanceState)
//        binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        SharedPreferenceManager.init(this)
//
//        initViewModel()
//        setupObservers()
//        //viewModel.getUserLogin()
//
//
//
//    }
//
//    private fun setupObservers() {
//        viewModel.apiStateLoginUser.observe(this){
//
//            when(it){
//                is ApiState.Error -> TODO()
//                is ApiState.Success -> TODO()
//            }
//
//        }
//    }
//
//    private fun initViewModel() {
//        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
//        val factory = MainViewModelFactory(repo)
//        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
//    }
//
//    private fun initView() {
//
//
//
//        val isUserLoggedIn = SharedPreferenceManager.getBoolean(SharedPreferenceManager.KEY_IS_LOGGED_IN)
//        if(isUserLoggedIn){
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//        else{
////            startActivity(Intent(this, LoginActivity::class.java))
//
//            supportFragmentManager.beginTransaction()
//                .add(R.id.myContainer, LoginFragment())
//                .commit()
////                .addToBackStack("LoginFragment")
//
////            finish()
//        }
//    }
//}