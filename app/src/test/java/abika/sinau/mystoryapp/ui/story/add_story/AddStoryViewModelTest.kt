package abika.sinau.mystoryapp.ui.story.add_story

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.DataSource
import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.domain.repository.StoryAppRepository
import abika.sinau.core.domain.usecase.StoryAppUsecase
import abika.sinau.mystoryapp.fake.FakeApiService
import abika.sinau.mystoryapp.fake.FakeDataSource
import abika.sinau.mystoryapp.fake.FakeRepository
import abika.sinau.mystoryapp.fake.FakeUsecase
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
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author by Abika Chairul Yusri on 10/29/2022
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddStoryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var dataSource: DataSource
    private lateinit var repository: StoryAppRepository
    private lateinit var usecase: StoryAppUsecase
    private lateinit var viewmodel: AddStoryViewModel

    @Before
    fun setUp() {
        apiService = FakeApiService()
        dataSource = FakeDataSource(apiService)
        repository = FakeRepository(dataSource)
        usecase = FakeUsecase(repository)
        viewmodel = AddStoryViewModel(usecase)
    }

    private val description = DataDummy.generateDummyDesc()
    private val latitude: Double = -6.1234213
    private val longitude: Double = 123.00123

    @Test
    fun `When Add Story Should Return Success`() = runBlocking {
        val dummyStory = DataDummy.generateDummyAddStory()
        val expectedAddStory: Resource<BaseResponseWrapper<Unit>> =
            Resource.Success(dummyStory)

        viewmodel.uploadImage(
            description = description,
            file = DataDummy.generateDummyPhoto(),
            latitude = latitude,
            longitude = longitude
        )
        val actual = viewmodel.resultAddStory.getOrAwaitValue()

        Assert.assertEquals(expectedAddStory.data, actual.data)

    }
}