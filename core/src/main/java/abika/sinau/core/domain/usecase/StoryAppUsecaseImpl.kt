package abika.sinau.core.domain.usecase

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.repository.StoryAppRepository
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import okhttp3.MultipartBody
import okhttp3.RequestBody


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

    override fun getListStoryPaging(): LiveData<PagingData<StoryListResponse>> {
        val query = StoryQuery()
        return repository.getListStoryPaging(query)
    }

    override suspend fun getListStory(): Resource<BaseResponseWrapper<StoryListResponse>> {
        val query = StoryQuery()
        query.size = 25
        query.location = 1
        return repository.getListStory(query)
    }

    override suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Resource<BaseResponseWrapper<Unit>> {
        return repository.postAddStoryAsUser(
            image = image,
            description = description,
            latitude = latitude,
            longitude = longitude
        )
    }
}