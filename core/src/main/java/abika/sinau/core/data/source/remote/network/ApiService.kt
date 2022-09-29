package abika.sinau.core.data.source.remote.network

import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequest
import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
interface ApiService {

    @POST("register")
    suspend fun postRegister(
        @Body registerRequest: RegisterRequest
    ): Response<BaseResponseWrapper<Unit>>

    @POST("login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ): Response<BaseResponseWrapper<LoginResultResponse>>

    @GET("stories")
    suspend fun getStoriesPagination(
        @QueryMap query: Map<String, @JvmSuppressWildcards Any>
    ): Response<BaseResponseWrapper<StoryListResponse>>

    @POST("stories")
    suspend fun postAddStoryAsUser(
        @Body addStoryRequest: AddStoryRequest
    ): Response<BaseResponseWrapper<Unit>>

    @POST("stories/guest")
    suspend fun postAddStoryAsGuest(
        @Body addStoryRequest: AddStoryRequest
    ): Response<BaseResponseWrapper<Unit>>

}