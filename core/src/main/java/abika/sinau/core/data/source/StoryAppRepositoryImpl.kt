package abika.sinau.core.data.source

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.RemoteDataSource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.repository.StoryAppRepository
import abika.sinau.core.utils.responseToResources
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
class StoryAppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : StoryAppRepository {
    override suspend fun postRegister(request: RegisterRequest): Resource<BaseResponseWrapper<Unit>> {
        return responseToResources(remoteDataSource.postRegister(request))
    }

    override suspend fun postLogin(request: LoginRequest): Resource<BaseResponseWrapper<LoginResultResponse>> {
        return responseToResources(remoteDataSource.postLogin(request))
    }

    override fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>> {
        return remoteDataSource.getListStoryPaging(query)
    }

    override suspend fun postAddStoryAsUser(request: AddStoryRequest): Resource<BaseResponseWrapper<Unit>> {
        return responseToResources(remoteDataSource.postAddStoryAsUser(request))
    }

    override suspend fun postAddStoryAsGuest(request: AddStoryRequest): Resource<BaseResponseWrapper<Unit>> {
        return responseToResources(remoteDataSource.postAddStoryAsGuest(request))
    }
}