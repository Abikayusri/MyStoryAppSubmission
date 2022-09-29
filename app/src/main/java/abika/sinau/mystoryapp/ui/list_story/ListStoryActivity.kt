package abika.sinau.mystoryapp.ui.list_story

import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.toastShort
import abika.sinau.mystoryapp.databinding.ActivityListStoryBinding
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListStoryActivity : BaseViewModelActivity<ListStoryViewModel, ActivityListStoryBinding>() {

    private val storyAdapter: ListStoryAdapter by lazy { ListStoryAdapter(this) }

    override val viewModelClass: Class<ListStoryViewModel>
        get() = ListStoryViewModel::class.java

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityListStoryBinding {
        return ActivityListStoryBinding.inflate(layoutInflater)
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: ListStoryViewModel) {
        lifecycleScope.launch {
            viewModel.apply {
                getListStoryPaging().collectLatest {
                    val datanya = it
                    val storyList = ArrayList<StoryListResponse>()

                    it.map { storyResponse ->
                        val story = StoryListResponse(
                            id = storyResponse.id,
                            name = storyResponse.name
                        )
                        storyList.add(story)
                    }

                    toastShort("storyList: $storyList")
                    toastShort("data paging: $it")
//                    storyAdapter.submitData(it)
                }

                getListStoryPagings().collectLatest {
                    val storyList = ArrayList<StoryListResponse>()

                    it.map { storyResponse ->
                        val story = StoryListResponse(
                            id = storyResponse.id,
                            name = storyResponse.name
                        )
                        storyList.add(story)
                    }

                    toastShort("storyList1: $storyList")

                    storyAdapter.submitData(it)
                }
            }
        }
    }

    override fun setupViews() {
        binding.rvListStory.adapter = storyAdapter

        storyAdapter.setOnItemClickListener(object : ListStoryAdapter.OnClickListener {
            override fun onClickItem(item: StoryListResponse) {
                toastShort("Menekan: ${item.name}")
//                val intent = Intent(this@ListStoryActivity, DetailActivity::class.java)
//                intent.putExtra("MOVIE_ID", item.id)
//                startActivity(intent)
            }
        })

        storyAdapter.addLoadStateListener { loadState ->
            binding.apply {
                rvListStory.isVisible = loadState.source.refresh is LoadState.NotLoading
                lavAnimations.isVisible = loadState.source.refresh is LoadState.Loading
//                btnRetry.isVisible = loadState.source.refresh is LoadState.Error
            }
        }
    }
}