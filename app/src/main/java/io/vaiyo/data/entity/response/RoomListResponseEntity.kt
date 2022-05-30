package io.vaiyo.data.entity.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.data.entity.RoomEntity
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.domain.model.base.ApiBaseResponse

data class RoomListResponseEntity(
    @SerializedName("rooms") val rooms: List<RoomEntity>? = null
): ApiBaseResponse()