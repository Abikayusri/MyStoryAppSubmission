package abika.sinau.mystoryapp.ui.register

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.utils.StoryConst.TYPE_EMAIL
import abika.sinau.core.utils.StoryConst.TYPE_NAME
import abika.sinau.core.utils.StoryConst.TYPE_PASSWORD
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.getTextString
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.toastShort
import abika.sinau.core.utils.visible
import abika.sinau.core.R
import abika.sinau.mystoryapp.databinding.ActivityRegisterBinding
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseViewModelActivity<RegisterViewModel, ActivityRegisterBinding>() {
    override val viewModelClass: Class<RegisterViewModel>
        get() = RegisterViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: RegisterViewModel) {
        viewModel.resultRegister.observe(lifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    toastShort(getString(R.string.label_register_success))
                    hideAnimation()
                    finish()
                }
                is Resource.Error -> {
                    toastShort(getString(R.string.label_register_failed))
                    hideAnimation()
                }
                is Resource.Loading -> {
                    showAnimation()
                }
            }
        }
    }

    override fun setupViews() {
        playAnimation()
        binding.apply {
            edRegisterName.setEditTextType(TYPE_NAME)
            edRegisterEmail.setEditTextType(TYPE_EMAIL)
            edRegisterPassword.setEditTextType(TYPE_PASSWORD)

            btnRegisterSubmit.setOnClickListener {
                if (edRegisterName.isNotEmptyAndError() && edRegisterEmail.isNotEmptyAndError() && edRegisterPassword.isNotEmptyAndError()) {
                    val request = RegisterRequest(
                        name = edRegisterName.getTextString(),
                        email = edRegisterEmail.getTextString(),
                        password = edRegisterPassword.getTextString()
                    )
                    viewModel.registerUser(request)
                } else if (!edRegisterName.isNotEmptyAndError()) {
                    edRegisterName.setErrorName()
                } else if (!edRegisterEmail.isNotEmptyAndError()) {
                    edRegisterEmail.setErrorEmail()
                } else if (!edRegisterPassword.isNotEmptyAndError()) {
                    edRegisterPassword.setErrorPassword()
                }
            }
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivRegisterAvatar, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val name = ObjectAnimator.ofFloat(binding.edRegisterName, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.edRegisterEmail, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.edRegisterPassword, View.ALPHA, 1f).setDuration(500)
        val submit = ObjectAnimator.ofFloat(binding.btnRegisterSubmit, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(name, email, password, submit)
            start()
        }
    }

    private fun hideAnimation() {
        binding.lavAnimations.gone()
    }

    private fun showAnimation() {
        binding.lavAnimations.visible()
    }
}