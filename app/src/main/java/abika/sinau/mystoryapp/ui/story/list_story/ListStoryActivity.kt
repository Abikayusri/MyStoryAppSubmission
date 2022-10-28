package abika.sinau.mystoryapp.ui.story.list_story

import abika.sinau.core.R
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.utils.StoryConst
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.visible
import abika.sinau.mystoryapp.databinding.ActivityListStoryBinding
import abika.sinau.mystoryapp.ui.authentication.login.LoginActivity
import abika.sinau.mystoryapp.ui.maps.MapsActivity
import abika.sinau.mystoryapp.ui.story.add_story.AddStoryActivity
import abika.sinau.mystoryapp.ui.story.detail_story.DetailStoryActivity
import abika.sinau.mystoryapp.ui.story.list_story.adapter.ListStoryAdapter
import abika.sinau.mystoryapp.ui.story.list_story.adapter.LoadingStateAdapter
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListStoryActivity : BaseViewModelActivity<ListStoryViewModel, ActivityListStoryBinding>() {

    private val storyAdapter: ListStoryAdapter by lazy { ListStoryAdapter() }

    override val viewModelClass: Class<ListStoryViewModel>
        get() = ListStoryViewModel::class.java

    private val successAddStoryResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                getListStoryPaging()
            }
        }

    private fun getListStoryPaging() {
        lifecycleScope.launch {
            viewModel.apply {
                getListStoryPaging().observe(this@ListStoryActivity) {
                    storyAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityListStoryBinding {
        return ActivityListStoryBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: ListStoryViewModel) {
        getListStoryPaging()
    }

    override fun setupViews() {
        setupToolbar()

        binding.apply {
            rvListStory.adapter = storyAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    storyAdapter.retry()
                }
            )

            storyAdapter.setOnItemClickListener(object : ListStoryAdapter.OnClickListener {
                override fun onClickItem(item: StoryListResponse) {
                    val intent = Intent(this@ListStoryActivity, DetailStoryActivity::class.java)
                    intent.putExtra(StoryConst.EXTRA_PARCELABLE_USER, item)
                    startActivity(intent)
                }
            })

            storyAdapter.addLoadStateListener { loadState ->
                binding.apply {
                    lavAnimations.isVisible =
                        loadState.source.refresh is LoadState.Loading && storyAdapter.itemCount <= 0
                }
            }

            fabAdd.setOnClickListener {
                val intent = Intent(this@ListStoryActivity, AddStoryActivity::class.java)
                successAddStoryResult.launch(intent)
            }
        }
    }

    private fun setupToolbar() {
        binding.inclAppBar.apply {
            llStartAttribute.gone()
            tvAppbarTitle.text = getString(R.string.label_user_welcome, sessionPrefs.userName)
            ivAppbarLogout.visible()
            ivAppbarLogout.setOnClickListener {
                showDialogLogout()
            }

            ivAppbarMaps.visible()
            ivAppbarMaps.setOnClickListener {
                startActivity(Intent(this@ListStoryActivity, MapsActivity::class.java))
            }
        }
    }

    private fun showDialogLogout() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.title_logout_dialog))
            .setPositiveButton(getString(R.string.label_sure)) { _, _ ->
                logoutAction()
            }
            .setNegativeButton(getString(R.string.label_no)) { _, _ -> }
            .show()
    }

    private fun logoutAction() {
        sessionPrefs.clearUserToken()
        startActivity(Intent(this@ListStoryActivity, LoginActivity::class.java))
        finishAffinity()
    }
}