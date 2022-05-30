package io.vaiyo.data.dataSource.remote.api

import io.vaiyo.data.dataSource.remote.NetworkEndpoint
import io.vaiyo.data.entity.response.*
import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import retrofit2.Response
import retrofit2.http.*

interface RoomApi {
    @GET(NetworkEndpoint.ROOM_LIST)
    suspend fun list(): Response<RoomListResponseEntity>

    @POST(NetworkEndpoint.ROOM_CREATE)
    suspend fun createRoom(
        @Body createRoomRequest: CreateRoomRequest
    ): Response<ApiBaseResponse>

    @DELETE(NetworkEndpoint.ROOM_DELETE)
    suspend fun deleteRoom(): Response<ApiBaseResponse>
}