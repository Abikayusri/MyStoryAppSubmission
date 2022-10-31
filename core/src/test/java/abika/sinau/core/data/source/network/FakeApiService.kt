package abika.sinau.core.data.source.network

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import abika.sinau.core.utils.DataDummy
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 10/31/2022
 */
class FakeApiService : ApiService {

    private val dummyLoginResponse = DataDummy.generateDummyLoginResponseSuccess()
    private val dummyRegisterResponse = DataDummy.generateDummyRegisterResponse()
    private val dummyListStoryResponse = DataDummy.generateDummyStoryListResponse()
    private val dummyAddStoryResponse = DataDummy.generateDummyAddStoryResponse()

    override suspend fun postRegister(registerRequest: RegisterRequest): Response<BaseResponseWrapper<Unit>> {
        return dummyRegisterResponse
    }

    override suspend fun postLogin(loginRequest: LoginRequest): Response<BaseResponseWrapper<LoginResultResponse>> {
        return dummyLoginResponse
    }

    override suspend fun getStoriesPagination(query: Map<String, Any>): Response<BaseResponseWrapper<StoryListResponse>> {
        return dummyListStoryResponse
    }

    override suspend fun postAddStoryAsUser(
        image: MultipartBody.Part,
        description: RequestBody,
        latitude: Double,
        longitude: Double
    ): Response<BaseResponseWrapper<Unit>> {
        val result = dummyAddStoryResponse
        return result
    }
}