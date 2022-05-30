package io.vaiyo.data.entity.response

import io.vaiyo.domain.enums.ReturnCode
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "response")
data class JoinMeetingResponseEntity(
    @field:Element(name = "returncode", required = false)
    @param:Element(name = "returncode", required = false)
    val returnCode: ReturnCode?,
    @field:Element(name = "meeting_id", required = false)
    @param:Element(name = "meeting_id", required = false)
    val meetingID: String?,
    @field:Element(name = "user_id", required = false)
    @param:Element(name = "user_id", required = false)
    val userID: String?,
    @field:Element(name = "auth_token", required = false)
    @param:Element(name = "auth_token", required = false)
    val authToken: String?,
    @field:Element(name = "session_token", required = false)
    @param:Element(name = "session_token", required = false)
    val sessionToken: String?,
    @field:Element(name = "url", required = false)
    @param:Element(name = "url", required = false)
    val url: String?,

    //errors
    @field:Element(name = "messageKey", required = false)
    @param:Element(name = "messageKey", required = false)
    val messageKey: String? = null,
    @field:Element(name = "message", required = false)
    @param:Element(name = "message", required = false)
    val message: String? = null,
)