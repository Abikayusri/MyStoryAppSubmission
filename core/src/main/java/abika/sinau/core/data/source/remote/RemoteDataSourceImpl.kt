package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.local.db.StoryDatabase
import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.paging.ListStoryRemoteMediator
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private val storyDatabase: StoryDatabase
) : RemoteDataSource {

    override suspend fun postRegister(request: RegisterRequest): Response<BaseResponseWrapper<Unit>> {
        return apiService.postRegister(request)
    }

    override suspend fun postLogin(request: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>> {
        return apiService.postLogin(request)
    }

//    override fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>> {
//        return Pager(config = PagingConfig(
//            pageSize = 10, enablePlaceholders = false
//        ), pagingSourceFactory = {
//            ListStoryPagingSource(
//                query = query,
//                apiService = apiService
//            )
//        }).liveData
//    }

    override fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = ListStoryRemoteMediator(
                query = query,
                database = storyDatabase,
                apiService = apiService
            ),
            pagingSourceFactory = {
                storyDatabase.storyDao().getAllStory()
            },
        ).liveData
    }

    override suspend fun getListStory(query: StoryQuery): Response<BaseResponseWrapper<StoryListResponse>> {
        return apiService.getStoriesPagination(query.toMap())
    }

    override suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Response<BaseResponseWrapper<Unit>> {
        return apiService.postAddStoryAsUser(image, description, latitude, longitude)
    }
}