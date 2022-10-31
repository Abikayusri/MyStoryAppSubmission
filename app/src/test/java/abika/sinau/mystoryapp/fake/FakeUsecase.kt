package abika.sinau.mystoryapp.fake

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.repository.StoryAppRepository
import abika.sinau.core.domain.usecase.StoryAppUsecase
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import okhttp3.MultipartBody
import okhttp3.RequestBody


/**
 * @author by Abika Chairul Yusri on 10/31/2022
 */
class FakeUsecase(private val repository: StoryAppRepository) : StoryAppUsecase {
    override suspend fun postRegisterUseCase(request: RegisterRequest): Resource<BaseResponseWrapper<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun postLoginUseCase(request: LoginRequest): Resource<BaseResponseWrapper<LoginResultResponse>> {
        TODO("Not yet implemented")
    }

    override fun getListStoryPaging(): LiveData<PagingData<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListStory(): Resource<BaseResponseWrapper<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Resource<BaseResponseWrapper<Unit>> {
        return repository.postAddStoryAsUser(description, image, latitude, longitude)
    }
}