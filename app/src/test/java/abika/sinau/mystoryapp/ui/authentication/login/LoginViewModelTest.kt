package abika.sinau.mystoryapp.ui.authentication.login

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
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
class LoginViewModelTest {

    @Mock
    lateinit var usecase: StoryAppUsecase

    private lateinit var viewModel: LoginViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    lateinit var dummyEmail: String
    lateinit var dummyPassword: String

    @Before
    fun setUp() {
        viewModel = LoginViewModel(usecase)
    }

    @Test
    fun `when Login Should Return Success`() = runBlocking {
        dummyEmail = "katest+1@gmail.com"
        dummyPassword = "string123"

        val request = LoginRequest(
            email = dummyEmail,
            password = dummyPassword
        )

        val dummyLogin = DataDummy.generateDummyLoginSuccess()
        val expected: Resource<BaseResponseWrapper<LoginResultResponse>> =
            Resource.Success(dummyLogin)

        Mockito.`when`(usecase.postLoginUseCase(request)).thenReturn(expected)
        viewModel.loginUser(request)
        val actual: Resource<BaseResponseWrapper<LoginResultResponse>> =
            viewModel.resultLogin.getOrAwaitValue()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `when Login Should Return Failed`() = runBlocking {
        dummyEmail = "katest+1@gmail.com"
        dummyPassword = "string1234567"

        val request = LoginRequest(
            email = dummyEmail,
            password = dummyPassword
        )

        val expected: Resource<BaseResponseWrapper<LoginResultResponse>> =
            Resource.Error("true")

        Mockito.`when`(usecase.postLoginUseCase(request)).thenReturn(expected)
        viewModel.loginUser(request)
        val actual = viewModel.resultLogin.getOrAwaitValue()
        Assert.assertEquals(expected.message, (actual as Resource.Error).message)
    }
}