package abika.sinau.core.data.source.remote.network

import abika.sinau.core.data.source.remote.request.AddStoryRequest
import abika.sinau.core.data.source.remote.request.LoginRequest
import abika.sinau.core.data.source.remote.request.RegisterRequset
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.PagingResponseWrapper
import abika.sinau.core.data.source.remote.response.ResponseWrapper
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
        @Body registerRequest: RegisterRequset
    ): Response<ResponseWrapper<Unit>>

    @POST("login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ): Response<ResponseWrapper<LoginResultResponse>>

    @GET("stories")
    suspend fun getStories(): Response<ResponseWrapper<List<StoryListResponse>>>

    @GET("stories")
    suspend fun getStoriesPagination(
        @QueryMap query: Map<String, @JvmSuppressWildcards Any>
    ): Response<PagingResponseWrapper<StoryListResponse>>

    @POST("stories")
    suspend fun postAddStoryAsUser(
        @Body addStoryRequest: AddStoryRequest
    ): Response<ResponseWrapper<Unit>>

    @POST("stories/guest")
    suspend fun postAddStoryAsGuest(
        @Body addStoryRequest: AddStoryRequest
    ): Response<ResponseWrapper<Unit>>

}