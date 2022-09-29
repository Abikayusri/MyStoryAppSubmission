package abika.sinau.core.utils.base

import abika.sinau.core.data.source.local.SessionPrefsManager
import abika.sinau.core.utils.SessionPrefs
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/30/2022
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    @Inject
    @SessionPrefs
    lateinit var sessionPrefs: SessionPrefsManager

    protected lateinit var binding: VB
    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    protected abstract fun setupViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }
}