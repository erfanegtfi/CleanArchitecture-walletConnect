package io.vaiyo.domain.model.requests

import com.google.gson.annotations.SerializedName

data class CreateRoomRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("mute_on_join")
    val muteOnJoin: String,
    @SerializedName("require_moderator_approval")
    val requireModeratorApproval: String,
    @SerializedName("anyone_can_start")
    val anyoneCanStart: String,
    @SerializedName("all_join_moderator")
    val allJoinModerator: String,
    @SerializedName("recording")
    val recording: String,
)