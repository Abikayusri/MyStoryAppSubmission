package abika.sinau.mystoryapp.ui.authentication.login

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usecase: StoryAppUsecase
) : ViewModel() {
    private val _resultLogin = MutableLiveData<Resource<BaseResponseWrapper<LoginResultResponse>>>()
    val resultLogin: LiveData<Resource<BaseResponseWrapper<LoginResultResponse>>> get() = _resultLogin

    fun loginUser(request: LoginRequest) {
        viewModelScope.launch {
            _resultLogin.postValue(Resource.Loading())
            try {
                val result = usecase.postLoginUseCase(request)
                _resultLogin.postValue(result)
            } catch(error: Exception) {
                _resultLogin.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}