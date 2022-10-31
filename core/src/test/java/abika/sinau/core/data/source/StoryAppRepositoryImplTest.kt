package abika.sinau.core.data.source

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.network.FakeApiService
import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.utils.DataDummy
import abika.sinau.core.utils.MainDispatcherRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

/**
 * @author by Abika Chairul Yusri on 10/29/2022
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StoryAppRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var dataSource: DataSource
    private lateinit var repositoryImpl: StoryAppRepositoryImpl


    lateinit var dummyName: String
    private lateinit var dummyEmail: String
    lateinit var dummyPassword: String

    @Before
    fun setUp() {
        apiService = FakeApiService()
        dataSource = FakeDataSource(apiService)
        repositoryImpl = StoryAppRepositoryImpl(dataSource)
    }

    @Test
    fun `when login should success result`() = runBlocking {
        dummyEmail = "katest+1@gmail.com"
        dummyPassword = "string123"

        val request = LoginRequest(
            email = dummyEmail,
            password = dummyPassword
        )

        val dummyLogin = DataDummy.generateDummyLoginSuccess()
        val expected = Resource.Success(dummyLogin)
        val actual = repositoryImpl.postLogin(request)

        Assert.assertEquals(expected.data, actual.data)
    }

    @Test
    fun `when register should success result`() = runBlocking {
        dummyName = "katest66"
        dummyEmail = "katest+66@gmail.com"
        dummyPassword = "string1234"

        val request = RegisterRequest(
            name = dummyName,
            email = dummyEmail,
            password = dummyPassword
        )

        val dummyRegister = DataDummy.generateDummyRegister()
        val expected = Resource.Success(dummyRegister)
        val actual = repositoryImpl.postRegister(request)

        Assert.assertEquals(expected.data, actual.data)
    }

    @Test
    fun `when list story should success result`() = runBlocking {
        val query = StoryQuery()

        val dummyListStory = DataDummy.generateDummyBaseResponseWrapperStoryListResponse()
        val expected = Resource.Success(dummyListStory)
        val actual = repositoryImpl.getListStory(query)

        Assert.assertEquals(expected.data, actual.data)
    }

    @Test
    fun `when add story should success result`() = runBlocking {
        val description = "description1"

        val file = File("/asset/logo.jpg")
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("photo", file.name, requestFile)
        val latitude = -6.1234213
        val longitude = 123.00123

        val dummyAddStory = DataDummy.generateDummyAddStory()
        val expected = Resource.Success(dummyAddStory)
        val actual: Resource<BaseResponseWrapper<Unit>> = repositoryImpl.postAddStoryAsUser(
            description = description.toRequestBody("text/plain".toMediaTypeOrNull()),
            image = body,
            latitude = latitude,
            longitude = longitude
        )

        Assert.assertEquals(expected.data, actual.data)
    }
}
