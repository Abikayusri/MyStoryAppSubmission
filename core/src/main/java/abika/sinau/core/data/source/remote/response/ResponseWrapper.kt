package abika.sinau.core.data.source.remote.response

import com.google.gson.annotations.SerializedName


/**
 * @author by Abika Chairul Yusri on 9/27/2022
 */
data class ResponseWrapper<T : Any?>(
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("loginResult")
    val loginResult: T? = null
)

//"error": false,
//"message": "success",
//"loginResult": {
//    "userId": "user-8Q_icPhX3SqAb3sI",
//    "name": "Abika",
//    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLThRX2ljUGhYM1NxQWIzc0kiLCJpYXQiOjE2NjQyNTUyNTR9.O7Qq2y764dp1nSrqOGoSXqSuflo3albY47eekqVS0EI"
//}