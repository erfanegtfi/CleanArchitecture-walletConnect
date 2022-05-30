package io.vaiyo.domain.model.response

import io.vaiyo.domain.model.Room
import io.vaiyo.domain.model.base.ApiBaseResponse

data class RoomListResponse(
    val rooms: List<Room>? = null
): ApiBaseResponse()