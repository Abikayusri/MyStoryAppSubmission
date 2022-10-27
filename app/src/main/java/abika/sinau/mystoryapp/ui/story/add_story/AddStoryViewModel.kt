package abika.sinau.mystoryapp.ui.story.add_story

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/29/2022
 */

@HiltViewModel
class AddStoryViewModel @Inject constructor(
    private val usecase: StoryAppUsecase
) : ViewModel() {

    private val _resultAddStory = MutableLiveData<Resource<BaseResponseWrapper<Unit>>>()
    val resultAddStory: LiveData<Resource<BaseResponseWrapper<Unit>>> get() = _resultAddStory

    fun uploadImage(
        description: RequestBody,
        file: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            _resultAddStory.postValue(Resource.Loading())
            try {
                val result = usecase.postAddStoryAsUser(description, file, latitude, longitude)
                _resultAddStory.postValue(result)
            } catch (error: Exception) {
                _resultAddStory.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}