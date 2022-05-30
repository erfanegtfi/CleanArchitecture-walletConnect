package io.vaiyo.data.entity

import com.google.gson.annotations.SerializedName
import io.vaiyo.data.entity.UserEntity

data class RoomEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("name") val name: String? = null,
    @SerializedName("uid") val uid: String? = null,
    @SerializedName("bbb_id") val bbbId: String? = null,
    @SerializedName("sessions") val sessions: Int? = null,
    @SerializedName("last_session") val lastSession: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("room_settings") val roomSettings: String? = null,
    @SerializedName("moderator_pw") val moderatorPpw: String? = null,
    @SerializedName("attendee_pw") val attendeePw: String? = null,
    @SerializedName("access_code") val accessCode: String? = null,
    @SerializedName("deleted") val deleted: Boolean? = null,
    @SerializedName("moderator_access_code") val moderatorAccessCode: String? = null,
    @SerializedName("participates") val participates: List<ParticipateEntity>? = null,
    @SerializedName("total_recordings") val totalRecordings: Int? = null,
)