package abika.sinau.core.domain.repository

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
interface StoryAppRepository {
    suspend fun postRegister(request: RegisterRequest): Resource<BaseResponseWrapper<Unit>>
    suspend fun postLogin(request: LoginRequest): Resource<BaseResponseWrapper<LoginResultResponse>>
    fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>>
    suspend fun postAddStoryAsUser(description: RequestBody, image: MultipartBody.Part): Resource<BaseResponseWrapper<Unit>>
    suspend fun postAddStoryAsGuest(request: AddStoryRequest): Resource<BaseResponseWrapper<Unit>>
}