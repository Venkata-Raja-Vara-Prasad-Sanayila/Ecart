package com.venkata.org.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.venkata.org.databinding.DialogAddressBinding
import com.venkata.org.databinding.FragmentDeliveryBinding
import com.venkata.org.model.data.deliveryAddress.AddDeliveryAddressRequest
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class AddressDialog( context: Context, private val viewLifecycleOwner: ViewModelStoreOwner): Dialog(context) {

    private lateinit var binding: DialogAddressBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogAddressBinding.inflate(layoutInflater)
        window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)


        initViewModel()
//        setupObserver()
        initView()
        setContentView(binding.root)
    }

//    private fun setupObserver() {
//        viewModel.apiStateAddAddress.observe(viewLifecycleOwner){
//
//        }
//    }

    private fun initView() {

        with(binding) {


            btnSave.setOnClickListener {
                val title = edtAddressTitle.text.toString()
                val addressInfo = edtAddress.text.toString()
                    if (title.isEmpty() || addressInfo.isEmpty()){
                        Toast.makeText(context, "Please fill andSave", Toast.LENGTH_SHORT).show()
                    }
                else{
                    viewModel.addDeliveryAddress(AddDeliveryAddressRequest(444, title, addressInfo))
                    }
            }

            btnCancel.setOnClickListener {

                dismiss()
            }
        }
    }

    private fun initViewModel() {
        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(viewLifecycleOwner, factory)[MainViewModel::class.java]
    }

}