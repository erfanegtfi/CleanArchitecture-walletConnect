package io.vaiyo.data.dataSource.remote.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class HttpLoggerInterceptor @Inject constructor() {
    fun getIntercept() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}