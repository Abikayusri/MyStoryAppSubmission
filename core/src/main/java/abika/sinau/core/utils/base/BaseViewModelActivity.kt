package abika.sinau.core.utils.base

import abika.sinau.core.data.source.local.SessionPrefsManager
import abika.sinau.core.utils.SessionPrefs
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject


abstract class BaseViewModelActivity<VM : ViewModel, VB : ViewBinding> : AppCompatActivity() {

    @Inject
    @SessionPrefs
    lateinit var sessionPrefs: SessionPrefsManager

    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: Class<VM>
    protected lateinit var binding: VB
    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB
    protected abstract fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: VM)
    protected abstract fun setupViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        setupObservers(this, viewModel)
        setupViews()
    }
}