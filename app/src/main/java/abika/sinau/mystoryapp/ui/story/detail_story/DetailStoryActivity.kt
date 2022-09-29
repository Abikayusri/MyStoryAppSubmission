package abika.sinau.mystoryapp.ui.story.detail_story

import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.mystoryapp.databinding.ActivityDetailStoryBinding
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStoryActivity :
    BaseViewModelActivity<DetailStoryViewModel, ActivityDetailStoryBinding>() {
    override val viewModelClass: Class<DetailStoryViewModel>
        get() = DetailStoryViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityDetailStoryBinding {
        return ActivityDetailStoryBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: DetailStoryViewModel) {

    }

    override fun setupViews() {

    }
}