package io.vaiyo.data.entity.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.domain.enums.ReturnCode

data class MeetingListResponseEntity(
    @SerializedName("returncode")
    val returnCode: ReturnCode?,
    @SerializedName("meetings")
    val meetings: MeetingListMeetingsResponseEntity?,
)

data class MeetingListMeetingsResponseEntity(
    @SerializedName("meeting")
    val meetingList: List<MeetingEntity>?,
)

data class MeetingEntity(
    @SerializedName("meetingName")
    val meetingName: String?,
    @SerializedName("meetingID")
    val meetingID: String?,
    @SerializedName("internalMeetingID")
    val internalMeetingID: String?,
    @SerializedName("parentMeetingID")
    val parentMeetingID: String?,
    @SerializedName("attendeePW")
    val attendeePW: String?,
    @SerializedName("moderatorPW")
    val moderatorPW: String?,
    @SerializedName("createTime")
    val createTime: Long?,
    @SerializedName("startTime")
    val startTime: Long?,
    @SerializedName("endTime")
    val endTime: Long?,
    @SerializedName("participantCount")
    val participantCount: Int?,
    @SerializedName("listenerCount")
    val listenerCount: Int?,
    @SerializedName("voiceParticipantCount")
    val voiceParticipantCount: Int?,
    @SerializedName("videoCount")
    val videoCount: Int?,
    @SerializedName("maxUsers")
    val maxUsers: Int?,
    @SerializedName("moderatorCount")
    val moderatorCount: Int?,
    @SerializedName("createDate")
    val createDate: String?,
    @SerializedName("dialNumber")
    val dialNumber: String?,
    @SerializedName("voiceBridge")
    val voiceBridge: Int?,
    @SerializedName("hasUserJoined")
    val hasUserJoined: Boolean?,
    @SerializedName("hasBeenForciblyEnded")
    val hasBeenForciblyEnded: Boolean?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("running")
    val running: Boolean?,
    @SerializedName("recording")
    val recording: Boolean?,
)