package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.paging.ListStoryPagingSource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun postRegister(request: RegisterRequest): Response<BaseResponseWrapper<Unit>> {
        return apiService.postRegister(request)
    }
    override suspend fun postLogin(request: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>> {
        return apiService.postLogin(request)
    }

    override fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>> {
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = false
        ), pagingSourceFactory = {
            ListStoryPagingSource(
                query = query,
                apiService = apiService
            )
        }).liveData
    }

    override suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Response<BaseResponseWrapper<Unit>> {
        return apiService.postAddStoryAsUser(image, description, latitude, longitude)
    }

    override suspend fun postAddStoryAsGuest(request: AddStoryRequest): Response<BaseResponseWrapper<Unit>> {
        return apiService.postAddStoryAsGuest(request)
    }

}