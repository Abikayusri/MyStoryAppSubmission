package abika.sinau.mystoryapp.fake

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.mystoryapp.utils.DataDummy
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 10/31/2022
 */
class FakeApiService : ApiService {

    private val dummyAddStoryResponse = DataDummy.generateDummyAddStoryResponse()

    override suspend fun postRegister(registerRequest: RegisterRequest): Response<BaseResponseWrapper<Unit>> {
        TODO("Not yet implemented")
    }

    override suspend fun postLogin(loginRequest: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getStoriesPagination(query: Map<String, Any>): Response<BaseResponseWrapper<StoryListResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun postAddStoryAsUser(
        image: MultipartBody.Part,
        description: RequestBody,
        latitude: Double,
        longitude: Double
    ): Response<BaseResponseWrapper<Unit>> {
        return dummyAddStoryResponse
    }
}