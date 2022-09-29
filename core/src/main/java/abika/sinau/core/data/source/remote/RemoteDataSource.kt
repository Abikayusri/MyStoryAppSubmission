package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
interface RemoteDataSource {
    suspend fun postRegister(request: RegisterRequest): Response<BaseResponseWrapper<Unit>>
    suspend fun postLogin(request: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>>
    fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>>
    suspend fun postAddStoryAsUser(request: AddStoryRequest): Response<BaseResponseWrapper<Unit>>
    suspend fun postAddStoryAsGuest(request: AddStoryRequest): Response<BaseResponseWrapper<Unit>>
}