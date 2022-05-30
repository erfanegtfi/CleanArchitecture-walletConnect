package io.vaiyo.data.dataSource.remote.api

import io.vaiyo.data.entity.response.EndMeetingResponseEntity
import io.vaiyo.data.entity.response.InfoMeetingResponseEntity
import io.vaiyo.data.entity.response.IsMeetingRunningResponseEntity
import io.vaiyo.data.entity.response.JoinMeetingResponseEntity
import retrofit2.http.GET
import retrofit2.http.Url

interface MeetingApi {
    @GET
    suspend fun join(
        @Url url: String,
    ): JoinMeetingResponseEntity


    @GET
    suspend fun end(
        @Url url: String,
    ): EndMeetingResponseEntity

    @GET
    suspend fun info(
        @Url url: String,
    ): InfoMeetingResponseEntity

    @GET
    suspend fun isRunning(
        @Url url: String,
    ): IsMeetingRunningResponseEntity
}