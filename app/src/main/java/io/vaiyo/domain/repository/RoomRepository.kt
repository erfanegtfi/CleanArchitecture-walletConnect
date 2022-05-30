package io.vaiyo.domain.repository

import io.vaiyo.domain.model.base.ApiBaseResponse
import io.vaiyo.domain.model.requests.CreateRoomRequest
import io.vaiyo.domain.model.response.RoomListResponse
import io.vaiyo.domain.model.viewState.ApiCallState
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun createRoom(createRoomRequest: CreateRoomRequest): Flow<ApiCallState<ApiBaseResponse>>
    fun roomList(): Flow<ApiCallState<RoomListResponse>>
    fun deleteRoom(): Flow<ApiCallState<ApiBaseResponse>>
}