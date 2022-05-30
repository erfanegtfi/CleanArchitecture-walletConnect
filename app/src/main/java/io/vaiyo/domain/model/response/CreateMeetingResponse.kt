package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

@Deprecated("")
data class CreateMeetingResponse(
    val returnCode: ReturnCode?, //SUCCESS
    val meetingID: String?,
    val internalMeetingID: String?,
    val parentMeetingID: String?,
    val attendeePW: String?,
    val moderatorPW: String?,
    val createTime: Long?,
    val createDate: String?,
    val dialNumber: String?,
    val voiceBridge: Int?,
    val hasUserJoined: Boolean?,
    val hasBeenForciblyEnded: Boolean?,
    val duration: Int?,

    )
