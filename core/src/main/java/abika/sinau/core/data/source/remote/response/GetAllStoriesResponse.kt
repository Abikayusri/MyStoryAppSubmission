package abika.sinau.core.data.source.remote.response


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetAllStoriesResponse(
    @SerializedName("error")
    val error: Boolean? = null,
    @SerializedName("listStory")
    val listStory: List<Story?>? = null,
    @SerializedName("message")
    val message: String? = null
) : Parcelable {
    @Parcelize
    data class Story(
        @SerializedName("createdAt")
        val createdAt: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("lat")
        val lat: Double? = null,
        @SerializedName("lon")
        val lon: Double? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("photoUrl")
        val photoUrl: String? = null
    ) : Parcelable
}