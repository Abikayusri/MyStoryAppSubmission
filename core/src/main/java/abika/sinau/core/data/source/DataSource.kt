package abika.sinau.core.data.source

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
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
interface DataSource {
    suspend fun postRegister(request: RegisterRequest): Response<BaseResponseWrapper<Unit>>
    suspend fun postLogin(request: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>>
    fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>>
    suspend fun getListStory(query: StoryQuery): Response<BaseResponseWrapper<StoryListResponse>>
    suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Response<BaseResponseWrapper<Unit>>
}