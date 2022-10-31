package abika.sinau.mystoryapp.fake

import abika.sinau.core.data.source.DataSource
import abika.sinau.core.data.source.remote.network.ApiService
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
 * @author by Abika Chairul Yusri on 10/31/2022
 */
class FakeDataSource(private val apiService: ApiService) : DataSource {
    override suspend fun postRegister(request: RegisterRequest): Response<BaseResponseWrapper<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun postLogin(request: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>> {
        TODO("Not yet implemented")
    }

    override fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListStory(query: StoryQuery): Response<BaseResponseWrapper<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Response<BaseResponseWrapper<Unit>> {
        return apiService.postAddStoryAsUser(
            image = image,
            description = description,
            latitude = latitude,
            longitude = longitude
        )
    }

}