package abika.sinau.mystoryapp.utils

import abika.sinau.core.data.source.remote.response.BaseResponseWrapper
import abika.sinau.core.data.source.remote.response.LoginResultResponse
import abika.sinau.core.data.source.remote.response.StoryListResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response


object DataDummy {
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

    fun generateDummyRegisterSuccess(): BaseResponseWrapper<Unit> {
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

    fun generateDummyPhoto() : MultipartBody.Part {
        return MultipartBody.Part.createFormData("DummyPhotoUrl", "https://story-api.dicoding.dev/images/stories/photos-1111111111111__aaaaaaa.jpg")
    }

    fun generateDummyDesc() : RequestBody {
        return "dummyDesc".toRequestBody("text/plain".toMediaType())
    }

    // region fake api service

    fun generateDummyAddStoryResponse(): Response<BaseResponseWrapper<Unit>> {
        return Response.success(generateDummyAddStory())
    }

    // endregion
}