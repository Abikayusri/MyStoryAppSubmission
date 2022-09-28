package abika.sinau.mystoryapp.ui.authentication

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.utils.StoryConst.TYPE_EMAIL
import abika.sinau.core.utils.StoryConst.TYPE_PASSWORD
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.visible
import abika.sinau.mystoryapp.databinding.ActivityLoginBinding
import abika.sinau.mystoryapp.ui.list_story.ListStoryActivity
import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity :
    BaseViewModelActivity<LoginViewModel, ActivityLoginBinding>() {

    override val viewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun setupObservers(
        lifecycleOwner: LifecycleOwner,
        viewModel: LoginViewModel
    ) {
        viewModel.apply {
            resultLogin.observe(lifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        hideAnimation()
                        sessionPrefs.hasLogin = true
                        sessionPrefs.userToken = response.data?.loginResult?.token.toString()

                        startActivity(
                            Intent(
                                this@LoginActivity,
                                ListStoryActivity::class.java
                            )
                        )
                        finishAffinity()
                    }
                    is Resource.Error -> {
                        hideAnimation()
                    }
                    is Resource.Loading -> {
                        showAnimation()
                    }
                }
            }
        }
    }

    private fun hideAnimation() {
        binding.lavAnimations.gone()
    }

    private fun showAnimation() {
        binding.lavAnimations.visible()
    }

    override fun setupViews() {
        binding.apply {
            etEmail.setEditTextType(TYPE_EMAIL)
            etPassword.setEditTextType(TYPE_PASSWORD)

            btnSubmit.setOnClickListener {
                if (etEmail.isNotEmptyAndError() && etPassword.isNotEmptyAndError()) {
                    val request = LoginRequest(
                        email = etEmail.text.toString(),
                        password = etPassword.text.toString()
                    )

                    viewModel.loginUser(request)
                } else if (!etPassword.isNotEmptyAndError()) {
                    etPassword.setErrorPassword()
                } else if (etEmail.isNotEmptyAndError()) {
                    etEmail.setErrorEmail()
                }
            }
        }
    }
}