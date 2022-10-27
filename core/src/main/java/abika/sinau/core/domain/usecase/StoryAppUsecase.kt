package abika.sinau.core.domain.usecase

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import okhttp3.MultipartBody
import okhttp3.RequestBody


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
interface StoryAppUsecase {
    suspend fun postRegisterUseCase(request: RegisterRequest): Resource<BaseResponseWrapper<Unit>>
    suspend fun postLoginUseCase(request: LoginRequest): Resource<BaseResponseWrapper<LoginResultResponse>>
    fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>>
    suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Resource<BaseResponseWrapper<Unit>>

    suspend fun postAddStoryAsGuest(request: AddStoryRequest): Resource<BaseResponseWrapper<Unit>>
}