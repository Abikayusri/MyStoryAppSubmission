package abika.sinau.mystoryapp.ui.maps

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 10/27/2022
 */

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val usecase: StoryAppUsecase
) : ViewModel() {

    private val _resultListStory = MutableLiveData<Resource<BaseResponseWrapper<StoryListResponse>>>()
    val resultListStory: LiveData<Resource<BaseResponseWrapper<StoryListResponse>>> get()= _resultListStory

    fun getListStory() {
        viewModelScope.launch {
            _resultListStory.postValue(Resource.Loading())
            try {
                val result: Resource<BaseResponseWrapper<StoryListResponse>> =
                    usecase.getListStory()
                _resultListStory.postValue(result)
            } catch (error: Exception) {
                _resultListStory.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}