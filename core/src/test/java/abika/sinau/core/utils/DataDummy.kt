package abika.sinau.core.utils

import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response


object DataDummy {

    // region baseResponse

    fun generateDummyQuoteResponse(): List<StoryListResponse> {
        val items: MutableList<StoryListResponse> = arrayListOf()
        for (i in 0..10) {
            val story = StoryListResponse(
                id = "id+$i",
                createdAt = "createdAt+$i",
                description = "description+$i",
                lat = null,
                lon = null,
                name = "name+$i",
                photoUrl = "photoUrl+$i"
            )
            items.add(story)
        }
        return items
    }

    fun generateDummyBaseResponseWrapperStoryListResponse(): BaseResponseWrapper<StoryListResponse> {
        val items: MutableList<StoryListResponse> = arrayListOf()
        for (i in 0..10) {
            val story = StoryListResponse(
                id = "id+$i",
                createdAt = "createdAt+$i",
                description = "description+$i",
                lat = null,
                lon = null,
                name = "name+$i",
                photoUrl = "photoUrl+$i"
            )
            items.add(story)
        }

        return BaseResponseWrapper(listStory = items)
    }

    fun generateDummyMultipartFile(): MultipartBody.Part {
        val dummyText = "text"
        return MultipartBody.Part.create(dummyText.toRequestBody())
    }

    fun generateDummyRequestBody(): RequestBody {
        val dummyText = "text"
        return dummyText.toRequestBody()
    }

    fun generateDummyLoginSuccess(): BaseResponseWrapper<LoginResultResponse> {
        val result = LoginResultResponse(
            userId = "userId",
            name = "Katest1",
            token = "token"
        )

        return BaseResponseWrapper(
            loginResult = result,
            error = "false",
            message = "Success"
        )
    }

    fun generateDummyRegister(): BaseResponseWrapper<Unit> {
        return BaseResponseWrapper(
            loginResult = Unit,
            error = "false",
            message = "Success"
        )
    }

    fun generateDummyAddStory(): BaseResponseWrapper<Unit> {
        return BaseResponseWrapper(
            loginResult = Unit,
            error = "false",
            message = "Story created successfully!"
        )
    }

    // endregion

    // region fake api service

    fun generateDummyLoginResponseSuccess(): Response<BaseResponseWrapper<LoginResultResponse>> {
        return Response.success(generateDummyLoginSuccess())
    }

    fun generateDummyRegisterResponse(): Response<BaseResponseWrapper<Unit>> {
        return Response.success(generateDummyRegister())
    }

    fun generateDummyStoryListResponse(): Response<BaseResponseWrapper<StoryListResponse>> {
        return Response.success(generateDummyBaseResponseWrapperStoryListResponse())
    }

    fun generateDummyAddStoryResponse(): Response<BaseResponseWrapper<Unit>> {
        return Response.success(generateDummyAddStory())
    }

    // endregion
}