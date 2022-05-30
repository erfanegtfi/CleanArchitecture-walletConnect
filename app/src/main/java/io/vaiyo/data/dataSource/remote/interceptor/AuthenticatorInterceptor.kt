package io.vaiyo.data.dataSource.remote.interceptor

import io.vaiyo.data.dataSource.remote.NetworkConfig
import io.vaiyo.domain.abstraction.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticatorInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    private val credentials: String
        get() = sessionManager.getToken()


    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
            request = request.newBuilder()
                .header(NetworkConfig.AUTHORIZATION, "${NetworkConfig.BEARER} eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxNTR9.KgeCYfk3sm7UM8lD4wZtPXdkzULh5PIxbiPTPnZzMN4")//eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxMDB9.h7VBa9z5g8yQbvxwlG11lOuD07lkmjmVBAwoMhrfPT4
                .build()
        return chain.proceed(request)
    }
}