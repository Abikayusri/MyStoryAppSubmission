package abika.sinau.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
data class BaseResponseWrapper<T : Any?>(
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("loginResult")
    val loginResult: T? = null,
    @SerializedName("listStory")
    val listStory: List<T>? = null
)