package abika.sinau.mystoryapp.ui.authentication

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.utils.StoryConst.TYPE_EMAIL
import abika.sinau.core.utils.StoryConst.TYPE_PASSWORD
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.getTextString
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.visible
import abika.sinau.mystoryapp.databinding.ActivityLoginBinding
import abika.sinau.mystoryapp.ui.list_story.ListStoryActivity
import abika.sinau.mystoryapp.ui.register.RegisterActivity
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
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
            edLoginEmail.setEditTextType(TYPE_EMAIL)
            edLoginPassword.setEditTextType(TYPE_PASSWORD)

            btnLoginSubmit.setOnClickListener {
                if (edLoginEmail.isNotEmptyAndError() && edLoginPassword.isNotEmptyAndError()) {
                    val request = LoginRequest(
                        email = edLoginEmail.getTextString(),
                        password = edLoginPassword.getTextString()
                    )

                    viewModel.loginUser(request)
                } else if (!edLoginPassword.isNotEmptyAndError()) {
                    edLoginPassword.setErrorPassword()
                } else if (edLoginEmail.isNotEmptyAndError()) {
                    edLoginEmail.setErrorEmail()
                }
            }

            tvLoginRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }

        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLoginAvatar, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val email = ObjectAnimator.ofFloat(binding.edLoginEmail, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.edLoginPassword, View.ALPHA, 1f).setDuration(500)
        val submit = ObjectAnimator.ofFloat(binding.btnLoginSubmit, View.ALPHA, 1f).setDuration(500)
        val register = ObjectAnimator.ofFloat(binding.tvLoginRegister, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(email, password, submit, register)
            start()
        }
    }
}