package abika.sinau.core.domain.usecase

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequset
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.ResponseWrapper
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.repository.StoryAppRepository
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
class StoryAppUsecaseImpl(
    private val repository: StoryAppRepository
) : StoryAppUsecase {
    override suspend fun postRegisterUseCase(request: RegisterRequset): Resource<ResponseWrapper<Unit>> {
        return repository.postRegister(request)
    }

    override suspend fun postLoginUseCase(request: LoginRequest): Resource<ResponseWrapper<LoginResultResponse>> {
        return repository.postLogin(request)
    }

    override suspend fun getListStory(): Resource<ResponseWrapper<List<StoryListResponse>>> {
        return repository.getListStory()
    }

    override fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>> {
        return repository.getListStoryPaging(query)
    }

    override suspend fun postAddStoryAsUser(request: AddStoryRequest): Resource<ResponseWrapper<Unit>> {
        return repository.postAddStoryAsUser(request)
    }

    override suspend fun postAddStoryAsGuest(request: AddStoryRequest): Resource<ResponseWrapper<Unit>> {
        return repository.postAddStoryAsGuest(request)
    }

}