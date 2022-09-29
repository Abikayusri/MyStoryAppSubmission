package abika.sinau.mystoryapp.ui.story.add_story

import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.mystoryapp.databinding.ActivityAddStoryBinding
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStoryActivity : BaseViewModelActivity<AddStoryViewModel, ActivityAddStoryBinding>() {
    override val viewModelClass: Class<AddStoryViewModel>
        get() = AddStoryViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityAddStoryBinding {
        return ActivityAddStoryBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: AddStoryViewModel) {

    }

    override fun setupViews() {

    }
}