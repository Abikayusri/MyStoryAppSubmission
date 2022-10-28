package abika.sinau.mystoryapp.ui.story.list_story

import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */

@HiltViewModel
class ListStoryViewModel @Inject constructor(
    private val usecase: StoryAppUsecase,
) : ViewModel() {

    fun getListStoryPaging(): LiveData<PagingData<StoryListResponse>> {
        val query = StoryQuery()
        return usecase.getListStoryPaging(query).cachedIn(viewModelScope)
    }
}