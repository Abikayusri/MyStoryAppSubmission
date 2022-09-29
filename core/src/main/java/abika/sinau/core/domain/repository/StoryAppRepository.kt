package abika.sinau.core.domain.repository

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequset
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.ResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
interface StoryAppRepository {
    suspend fun postRegister(request: RegisterRequset): Resource<ResponseWrapper<Unit>>
    suspend fun postLogin(request: LoginRequest): Resource<ResponseWrapper<LoginResultResponse>>
    suspend fun getListStory(): Resource<ResponseWrapper<List<StoryListResponse>>>
    fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>>
    suspend fun postAddStoryAsUser(request: AddStoryRequest): Resource<ResponseWrapper<Unit>>
    suspend fun postAddStoryAsGuest(request: AddStoryRequest): Resource<ResponseWrapper<Unit>>
}