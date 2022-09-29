package abika.sinau.core.utils.base

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding


abstract class BaseViewModelActivity<VM : ViewModel, VB : ViewBinding> : BaseActivity<VB>() {

    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: Class<VM>
    protected abstract fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: VM)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
        setupObservers(this, viewModel)
    }
}