package abika.sinau.mystoryapp.ui.register

import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.mystoryapp.R
import abika.sinau.mystoryapp.databinding.ActivityRegisterBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
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

    }

    override fun setupViews() {

    }
}