package abika.sinau.mystoryapp.ui.authentication.register

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.domain.usecase.StoryAppUsecase
import abika.sinau.mystoryapp.utils.DataDummy
import abika.sinau.mystoryapp.utils.MainDispatcherRule
import abika.sinau.mystoryapp.utils.getOrAwaitValue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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
class RegisterViewModelTest {
    @Mock
    lateinit var usecase: StoryAppUsecase

    private lateinit var viewModel: RegisterViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    lateinit var dummyName: String
    lateinit var dummyEmail: String
    lateinit var dummyPassword: String

    @Before
    fun setUp() {
        viewModel = RegisterViewModel(usecase)
    }

    @Test
    fun `When Register Should Return Success`() = runBlocking {
        dummyName = "katest66"
        dummyEmail = "katest+66@gmail.com"
        dummyPassword = "string1234"

        val request = RegisterRequest(
            name = dummyName,
            email = dummyEmail,
            password = dummyPassword
        )

        val dummyRegister = DataDummy.generateDummyRegisterSuccess()
        val expected: Resource<BaseResponseWrapper<Unit>> = Resource.Success(dummyRegister)

        Mockito.`when`(usecase.postRegisterUseCase(request)).thenReturn(expected)
        viewModel.registerUser(request)
        val actual = viewModel.resultRegister.getOrAwaitValue()
        assertEquals(expected.data?.error, actual.data?.error)
    }


    @Test
    fun `When Register Should Return Failed`() = runBlocking {
        dummyName = "katest1"
        dummyEmail = "katest+1@gmail.com"
        dummyPassword = "string1234"

        val request = RegisterRequest(
            name = dummyName,
            email = dummyEmail,
            password = dummyPassword
        )

        val expected: Resource<BaseResponseWrapper<Unit>> =
            Resource.Error("true")

        Mockito.`when`(usecase.postRegisterUseCase(request)).thenReturn(expected)
        viewModel.registerUser(request)
        val actual = viewModel.resultRegister.getOrAwaitValue()
        assertEquals(expected.message, (actual as Resource.Error).message)
    }
}