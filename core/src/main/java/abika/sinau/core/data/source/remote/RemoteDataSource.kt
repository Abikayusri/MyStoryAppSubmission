package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequset
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.ResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
interface RemoteDataSource {
    suspend fun postRegister(request: RegisterRequset): Response<ResponseWrapper<Unit>>
    suspend fun postLogin(request: LoginRequest): Response<ResponseWrapper<LoginResultResponse>>
    suspend fun getListStory(): Response<ResponseWrapper<List<StoryListResponse>>>
    fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>>
    suspend fun postAddStoryAsUser(request: AddStoryRequest): Response<ResponseWrapper<Unit>>
    suspend fun postAddStoryAsGuest(request: AddStoryRequest): Response<ResponseWrapper<Unit>>
}