package io.vaiyo.data.dataSource.remote

import io.vaiyo.BuildConfig

object NetworkConfig {
    const val SHARED_SECRET_KEY = BuildConfig.SHARED_SECRET_KEY
    const val REQUEST_TIMEOUT_DURATION = 60L
    const val BASE_URL = "https://staging.vaiyo.io/api/v1/"
    const val BASE_URL_ROOM = "https://staging.vaiyo.io/" // join room url
    const val BASE_URL_RECORDING = "https://staging.vaiyo.io/playback/presentation/2.0/playback.html?meetingId="
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "JWT"
}