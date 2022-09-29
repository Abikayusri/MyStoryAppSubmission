package abika.sinau.mystoryapp.ui.authentication.register

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/28/2022
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val usecase: StoryAppUsecase
) : ViewModel() {
    private val _resultRegister = MutableLiveData<Resource<BaseResponseWrapper<Unit>>>()
    val resultRegister: LiveData<Resource<BaseResponseWrapper<Unit>>> get() = _resultRegister

    fun registerUser(request: RegisterRequest) {
        viewModelScope.launch {
            _resultRegister.postValue(Resource.Loading())
            try {
                val result = usecase.postRegisterUseCase(request)
                _resultRegister.postValue(result)
            } catch (error: Exception) {
                _resultRegister.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}