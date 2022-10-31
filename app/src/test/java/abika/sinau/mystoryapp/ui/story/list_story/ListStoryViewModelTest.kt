package abika.sinau.mystoryapp.ui.story.list_story

import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.usecase.StoryAppUsecase
import abika.sinau.mystoryapp.ui.story.list_story.adapter.ListStoryAdapter
import abika.sinau.mystoryapp.utils.DataDummy
//import abika.sinau.core.utils.DataDummy
import abika.sinau.mystoryapp.utils.ListStoryPagingSource
import abika.sinau.mystoryapp.utils.MainDispatcherRule
import abika.sinau.mystoryapp.utils.getOrAwaitValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author by Abika Chairul Yusri on 10/29/2022
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListStoryViewModelTest {

    @Mock
    lateinit var usecase: StoryAppUsecase

    private lateinit var viewModel: ListStoryViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = ListStoryViewModel(usecase)
    }

    @Test
    fun `when get story and return success`() = runTest {
        val dummyStory = DataDummy.generateDummyQuoteResponse()
        val data: PagingData<StoryListResponse> = ListStoryPagingSource.snapshot(dummyStory)
        val expectedStory = MutableLiveData<PagingData<StoryListResponse>>()
        expectedStory.value = data
        Mockito.`when`(usecase.getListStoryPaging()).thenReturn(expectedStory)

        val listViewModel = ListStoryViewModel(usecase)
        val actualStory: PagingData<StoryListResponse> =
            listViewModel.getListStoryPaging().getOrAwaitValue()

        val differ = AsyncPagingDataDiffer(
            diffCallback = ListStoryAdapter.DIFF_CALLBACK,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = Dispatchers.Main,
        )
        differ.submitData(actualStory)

        Assert.assertNotNull(differ.snapshot())
        Assert.assertEquals(dummyStory, differ.snapshot())
        Assert.assertEquals(dummyStory.size, differ.snapshot().size)
        Assert.assertEquals(dummyStory[0].name, differ.snapshot()[0]?.name)
    }

    private val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}