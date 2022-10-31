package abika.sinau.mystoryapp.fake

import abika.sinau.core.data.Resource
import abika.sinau.core.data.source.DataSource
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.request.StoryQuery
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.domain.repository.StoryAppRepository
import abika.sinau.core.utils.responseToResources
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import okhttp3.MultipartBody
import okhttp3.RequestBody


/**
 * @author by Abika Chairul Yusri on 10/31/2022
 */
class FakeRepository(private val dataSource: DataSource) : StoryAppRepository {
    override suspend fun postRegister(request: RegisterRequest): Resource<BaseResponseWrapper<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun postLogin(request: LoginRequest): Resource<BaseResponseWrapper<LoginResultResponse>> {
        TODO("Not yet implemented")
    }

    override fun getListStoryPaging(query: StoryQuery): LiveData<PagingData<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getListStory(query: StoryQuery): Resource<BaseResponseWrapper<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun postAddStoryAsUser(
        description: RequestBody,
        image: MultipartBody.Part,
        latitude: Double,
        longitude: Double
    ): Resource<BaseResponseWrapper<Unit>> {
        return responseToResources(
            dataSource.postAddStoryAsUser(
                image = image,
                description = description,
                latitude = latitude,
                longitude = longitude
            )
        )
    }
}