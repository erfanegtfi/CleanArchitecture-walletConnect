package io.vaiyo.domain.model.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.domain.enums.AttendeeClientType
import io.vaiyo.domain.enums.AttendeeRole
import io.vaiyo.domain.enums.ReturnCode

data class InfoMeetingResponse(
    val returnCode: ReturnCode? = null,
    val meetingName: String? = null,
    val meetingID: String? = null,
    val internalMeetingID: String? = null,
    val createTime: String? = null,
    val createDate: String? = null,
    val voiceBridge: String? = null,
    val dialNumber: String? = null,
    val attendeePW: String? = null,
    val moderatorPW: String? = null,
    val running: Boolean? = null,
    val duration: Long? = null,
    val hasUserJoined: Boolean? = null,
    val recording: Boolean? = null,
    val isBreakout: Boolean? = null,
    val hasBeenForciblyEnded: Boolean? = null,
    val startTime: Long? = null,
    val endTime: Long? = null,
    val participantCount: Int? = null,
    val listenerCount: Int? = null,
    val voiceParticipantCount: Int? = null,
    val videoCount: Int? = null,
    val maxUsers: Int? = null,
    val moderatorCount: Int? = null,
    val attendees: AttendeesResponse? = null,
    val messageKey: String? = null,
    val message: String? = null,
)

data class AttendeesResponse(
    val attendee: List<AttendeeResponse>? = null,
)


data class AttendeeResponse(
    val userID: String? = null,
    val fullName: String? = null,
    val role: AttendeeRole? = null,
    val isPresenter: Boolean? = null,
    val isListeningOnly: Boolean? = null,
    val hasJoinedVoice: Boolean? = null,
    val hasVideo: Boolean? = null,
    val clientType: AttendeeClientType? = null,
)