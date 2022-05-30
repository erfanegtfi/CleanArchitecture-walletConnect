package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

@Deprecated("")
data class MeetingListResponse(
    val returnCode: ReturnCode?,
        val meetings: MeetingListMeetingsResponse?,
)
@Deprecated("")
data class MeetingListMeetingsResponse(
    val meetingList: List<Meeting>?,
)
@Deprecated("")
data class Meeting(
    val meetingName: String?,
    val meetingID: String?,
    val internalMeetingID: String?,
    val parentMeetingID: String?,
    val attendeePW: String?,
    val moderatorPW: String?,
    val createTime: Long?,
    val startTime: Long?,
    val endTime: Long?,
    val participantCount: Int?,
    val listenerCount: Int?,
    val voiceParticipantCount: Int?,
    val videoCount: Int?,
    val maxUsers: Int?,
    val moderatorCount: Int?,
    val createDate: String?,
    val dialNumber: String?,
    val voiceBridge: Int?,
    val hasUserJoined: Boolean?,
    val hasBeenForciblyEnded: Boolean?,
    val duration: Int?,
    val running: Boolean?,
    val recording: Boolean?,
)
