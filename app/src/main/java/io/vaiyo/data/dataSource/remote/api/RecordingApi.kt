package io.vaiyo.data.dataSource.remote.api

import io.vaiyo.data.dataSource.remote.NetworkEndpoint
import io.vaiyo.data.entity.response.*
import io.vaiyo.domain.model.base.ApiBaseResponse
import retrofit2.Response
import retrofit2.http.*

interface RecordingApi {

    @GET(NetworkEndpoint.RECORDINGS)
    suspend fun getAllRecordings(): Response<RecordingListResponseEntity>

    @GET(NetworkEndpoint.PUBLISH_RECORDINGS)
    suspend fun publishRecordings(
        @Query("recordID") meetingID: String,
        @Query("publish") recordID: String,
    ): Response<ApiBaseResponse>

    @GET(NetworkEndpoint.GET_RECORDING)
    suspend fun getRecording(
        @Path("meetingID") recordID: String,
    ): Response<RecordingListResponseEntity>

    @DELETE(NetworkEndpoint.DELETE_RECORDINGS)
    suspend fun deleteRecordings(
        @Path("meetingID") meetingID: String,
        @Path("recordID") recordID: String,
    ): Response<ApiBaseResponse>
}