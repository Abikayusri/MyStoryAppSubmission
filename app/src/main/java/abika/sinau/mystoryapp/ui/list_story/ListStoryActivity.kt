package abika.sinau.mystoryapp.ui.list_story

import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.mystoryapp.R
import abika.sinau.mystoryapp.databinding.ActivityListStoryBinding
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListStoryActivity : BaseViewModelActivity<ListStoryViewModel, ActivityListStoryBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_story)
    }

    override val viewModelClass: Class<ListStoryViewModel>
        get() = ListStoryViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityListStoryBinding {
        return ActivityListStoryBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: ListStoryViewModel) {

    }

    override fun setupViews() {

    }
}