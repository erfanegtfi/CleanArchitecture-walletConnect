package io.vaiyo.data.entity.response

import com.google.gson.annotations.SerializedName
import io.vaiyo.domain.enums.AttendeeClientType
import io.vaiyo.domain.enums.AttendeeRole
import io.vaiyo.domain.enums.ReturnCode
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "response", strict=false)
data class InfoMeetingResponseEntity(
    @field:Element(name = "returncode")
    @param:Element(name = "returncode")
    val returnCode: ReturnCode? = null,
    @field:Element(name = "meetingName", required = false)
    @param:Element(name = "meetingName", required = false)
    val meetingName: String? = null,
    @field:Element(name = "meetingID", required = false)
    @param:Element(name = "meetingID", required = false)
    val meetingID: String? = null,
    @field:Element(name = "internalMeetingID", required = false)
    @param:Element(name = "internalMeetingID", required = false)
    val internalMeetingID: String? = null,
    @field:Element(name = "createTime", required = false)
    @param:Element(name = "createTime", required = false)
    val createTime: String? = null,
    @field:Element(name = "createDate", required = false)
    @param:Element(name = "createDate", required = false)
    val createDate: String? = null,
    @field:Element(name = "voiceBridge", required = false)
    @param:Element(name = "voiceBridge", required = false)
    val voiceBridge: String? = null,
    @field:Element(name = "dialNumber", required = false)
    @param:Element(name = "dialNumber", required = false)
    val dialNumber: String? = null,
    @field:Element(name = "attendeePW", required = false)
    @param:Element(name = "attendeePW", required = false)
    val attendeePW: String? = null,
    @field:Element(name = "moderatorPW", required = false)
    @param:Element(name = "moderatorPW", required = false)
    val moderatorPW: String? = null,
    @field:Element(name = "running", required = false)
    @param:Element(name = "running", required = false)
    val running: Boolean? = null,
    @field:Element(name = "duration", required = false)
    @param:Element(name = "duration", required = false)
    val duration: Long? = null,
    @field:Element(name = "hasUserJoined", required = false)
    @param:Element(name = "hasUserJoined", required = false)
    val hasUserJoined: Boolean? = null,
    @field:Element(name = "recording", required = false)
    @param:Element(name = "recording", required = false)
    val recording: Boolean? = null,
    @field:Element(name = "isBreakout", required = false)
    @param:Element(name = "isBreakout", required = false)
    val isBreakout: Boolean? = null,
    @field:Element(name = "hasBeenForciblyEnded", required = false)
    @param:Element(name = "hasBeenForciblyEnded", required = false)
    val hasBeenForciblyEnded: Boolean? = null,
    @field:Element(name = "startTime", required = false)
    @param:Element(name = "startTime", required = false)
    val startTime: Long? = null,
    @field:Element(name = "endTime", required = false)
    @param:Element(name = "endTime", required = false)
    val endTime: Long? = null,
    @field:Element(name = "participantCount", required = false)
    @param:Element(name = "participantCount", required = false)
    val participantCount: Int? = null,
    @field:Element(name = "listenerCount", required = false)
    @param:Element(name = "listenerCount", required = false)
    val listenerCount: Int? = null,
    @field:Element(name = "voiceParticipantCount", required = false)
    @param:Element(name = "voiceParticipantCount", required = false)
    val voiceParticipantCount: Int? = null,
    @field:Element(name = "videoCount", required = false)
    @param:Element(name = "videoCount", required = false)
    val videoCount: Int? = null,
    @field:Element(name = "maxUsers", required = false)
    @param:Element(name = "maxUsers", required = false)
    val maxUsers: Int? = null,
    @field:Element(name = "moderatorCount", required = false)
    @param:Element(name = "moderatorCount", required = false)
    val moderatorCount: Int? = null,
    @field:Element(name = "metadata", required = false)
    @param:Element(name = "metadata", required = false)
    val metadata: String? = null,
    @field:ElementList(name = "attendees", inline=true, required = false)
    @param:ElementList(name = "attendees", inline=true, required = false)
//    @param:Path("attendees")
//    @field:Path("attendees")
    val attendees: List<AttendeeResponseEntity>? = null,

    //errors
    @field:Element(name = "messageKey", required = false)
    @param:Element(name = "messageKey", required = false)
    val messageKey: String? = null,
    @field:Element(name = "message", required = false)
    @param:Element(name = "message", required = false)
    val message: String? = null,
)

data class AttendeeResponseEntity(
    @field:Element(name = "userID", required = false)
    @param:Element(name = "userID", required = false)
    val userID: String? = null,
    @field:Element(name = "fullName", required = false)
    @param:Element(name = "fullName", required = false)
    val fullName: String? = null,
    @field:Element(name = "role", required = false)
    @param:Element(name = "role", required = false)
    val role: AttendeeRole? = null,
    @field:Element(name = "isPresenter", required = false)
    @param:Element(name = "isPresenter", required = false)
    val isPresenter: Boolean? = null,
    @field:Element(name = "isListeningOnly", required = false)
    @param:Element(name = "isListeningOnly", required = false)
    val isListeningOnly: Boolean? = null,
    @field:Element(name = "hasJoinedVoice", required = false)
    @param:Element(name = "hasJoinedVoice", required = false)
    val hasJoinedVoice: Boolean? = null,
    @field:Element(name = "hasVideo", required = false)
    @param:Element(name = "hasVideo", required = false)
    val hasVideo: Boolean? = null,
    @field:Element(name = "clientType", required = false)
    @param:Element(name = "clientType", required = false)
    val clientType: AttendeeClientType? = null,
)