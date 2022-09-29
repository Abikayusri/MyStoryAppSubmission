package abika.sinau.core.domain.usecase

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
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
    override suspend fun postRegisterUseCase(request: RegisterRequest): Resource<BaseResponseWrapper<Unit>> {
        return repository.postRegister(request)
    }

    override suspend fun postLoginUseCase(request: LoginRequest): Resource<BaseResponseWrapper<LoginResultResponse>> {
        return repository.postLogin(request)
    }

    override fun getListStoryPaging(query: StoryQuery): Flow<PagingData<StoryListResponse>> {
        return repository.getListStoryPaging(query)
    }

    override suspend fun postAddStoryAsUser(request: AddStoryRequest): Resource<BaseResponseWrapper<Unit>> {
        return repository.postAddStoryAsUser(request)
    }

    override suspend fun postAddStoryAsGuest(request: AddStoryRequest): Resource<BaseResponseWrapper<Unit>> {
        return repository.postAddStoryAsGuest(request)
    }

}