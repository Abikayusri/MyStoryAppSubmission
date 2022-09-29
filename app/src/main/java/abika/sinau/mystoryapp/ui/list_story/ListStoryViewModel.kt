package abika.sinau.mystoryapp.ui.list_story

import abika.sinau.core.data.source.remote.RemoteDataSource
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */

@HiltViewModel
class ListStoryViewModel @Inject constructor(
    private val usecase: StoryAppUsecase,
    private val remoteDataSouce: RemoteDataSource
) : ViewModel() {
    fun getListStoryPaging(
        location: Double? = 0.0
    ): Flow<PagingData<StoryListResponse>> {
        val query = StoryQuery(
            location = location
        )
        val dataUseCase = usecase.getListStoryPaging(query).cachedIn(viewModelScope)
        Timber.e("dataUsecase: $dataUseCase")
        return dataUseCase
    }

    fun getListStoryPagings(
        location: Double? = 0.0
    ): Flow<PagingData<StoryListResponse>> {
        val query = StoryQuery(
            location = location
        )

        return remoteDataSouce.getListStoryPaging(query).cachedIn(viewModelScope)
    }
}