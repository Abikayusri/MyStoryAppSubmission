package abika.sinau.mystoryapp.ui.authentication

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.toastShort
import abika.sinau.mystoryapp.R
import abika.sinau.mystoryapp.databinding.ActivityAuthenticationBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : BaseViewModelActivity<AuthenticationViewModel, ActivityAuthenticationBinding>() {

    override val viewModelClass: Class<AuthenticationViewModel>
        get() = AuthenticationViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityAuthenticationBinding {
        return ActivityAuthenticationBinding.inflate(layoutInflater)
    }

    override fun setupObservers(
        lifecycleOwner: LifecycleOwner,
        viewModel: AuthenticationViewModel
    ) {
        viewModel.apply {
            resultLogin.observe(lifecycleOwner) { response ->
                when(response) {
                    is Resource.Success -> {
                        toastShort("success: ${response.data}")
                        sessionPrefs.hasLogin = true
                        sessionPrefs.userToken = response.data?.loginResult?.token.toString()
                    }
                    is Resource.Error -> {
                        toastShort("error: ${response.message}")
                    }
                    is Resource.Loading -> {

                        toastShort("loading")
                    }
                }
            }
        }
    }

    override fun setupViews() {
        binding.apply {
            btnSubmit.setOnClickListener {
                val request= LoginRequest(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )

                viewModel.loginUser(request)
            }
        }
    }
}