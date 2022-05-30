package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

data class JoinMeetingResponse(
    val returnCode: ReturnCode?,
    val messageKey: String?,
    val message: String?,
    val meetingID: String?,
    val userID: String?,
    val authToken: String?,
    val sessionToken: String?,
    val url: String?,
)