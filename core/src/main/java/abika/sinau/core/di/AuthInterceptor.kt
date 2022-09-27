package abika.sinau.core.di

import abika.sinau.core.data.source.local.SessionPrefsManager
import abika.sinau.core.utils.SessionPrefs
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 9/26/2022
 */
class AuthInterceptor @Inject constructor(
    @SessionPrefs private val sharedPrefs: SessionPrefsManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val userToken =
            if (sharedPrefs.userToken.isEmpty()) "Bearer ${sharedPrefs.userToken}" else ""

        val request = chain.request().newBuilder()
            .header("Authorization", userToken)
            .build()
        return chain.proceed(request)
    }
}