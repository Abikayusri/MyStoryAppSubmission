package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.paging.ListStoryPagingSource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.ResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun postRegister(request: RegisterRequest): Response<ResponseWrapper<Unit>> {
        return apiService.postRegister(request)
    }
    override suspend fun postLogin(request: LoginRequest): Response<ResponseWrapper<LoginResultResponse>> {
        return apiService.postLogin(request)
    }

    override suspend fun getListStory(): Response<ResponseWrapper<List<StoryListResponse>>> {
        return apiService.getStories()
    }

    override fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>> {
        return Pager(config = PagingConfig(
            pageSize = 10, enablePlaceholders = false
        ), pagingSourceFactory = {
            ListStoryPagingSource(
                query = query,
                apiService = apiService
            )
        }).flow
    }

    override suspend fun postAddStoryAsUser(request: AddStoryRequest): Response<ResponseWrapper<Unit>> {
        return apiService.postAddStoryAsUser(request)
    }

    override suspend fun postAddStoryAsGuest(request: AddStoryRequest): Response<ResponseWrapper<Unit>> {
        return apiService.postAddStoryAsGuest(request)
    }

}