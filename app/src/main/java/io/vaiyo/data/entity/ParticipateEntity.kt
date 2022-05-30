package io.vaiyo.data.entity

import com.google.gson.annotations.SerializedName
import io.vaiyo.data.entity.UserEntity
import io.vaiyo.domain.model.Participate

data class ParticipateEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("room_id") val roomID: Int?,
    @SerializedName("provider") val provider: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("uid") val uid: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("last_login") val lastLogin: String?,
    @SerializedName("last_session") val lastSession: String?,
    @SerializedName("image") val image: ParticipateImageEntity?,
)

data class ParticipateImageEntity(
    @SerializedName("url") val url: String?,
)