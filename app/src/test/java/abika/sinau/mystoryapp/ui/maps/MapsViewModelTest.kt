package abika.sinau.mystoryapp.ui.maps

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.usecase.StoryAppUsecase
import abika.sinau.mystoryapp.utils.DataDummy
import abika.sinau.mystoryapp.utils.MainDispatcherRule
import abika.sinau.mystoryapp.utils.getOrAwaitValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
class MapsViewModelTest {

    @Mock
    lateinit var usecase: StoryAppUsecase

    private lateinit var viewmodel: MapsViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Before
    fun setUp() {
        viewmodel = MapsViewModel(usecase)
    }

    @Test
    fun `when List Story Should Return Success`() = runBlocking {
        val dummyStory = DataDummy.generateDummyBaseResponseWrapperStoryListResponse()
        val expectedStory: Resource<BaseResponseWrapper<StoryListResponse>> =
            Resource.Success(dummyStory)

        Mockito.`when`(usecase.getListStory()).thenReturn(expectedStory)
        viewmodel.getListStory()
        val actual: Resource<BaseResponseWrapper<StoryListResponse>> =
            viewmodel.resultListStory.getOrAwaitValue()

        Assert.assertEquals(expectedStory.data?.listStory, actual.data?.listStory)
    }

    @Test
    fun `when List Story Should Return Failed`() = runBlocking {
        val expectedStory: Resource<BaseResponseWrapper<StoryListResponse>> =
            Resource.Error("true")

        Mockito.`when`(usecase.getListStory()).thenReturn(expectedStory)
        viewmodel.getListStory()
        val actual: Resource<BaseResponseWrapper<StoryListResponse>> =
            viewmodel.resultListStory.getOrAwaitValue()

        Assert.assertEquals(expectedStory.message, (actual as Resource.Error).message)
    }
}
