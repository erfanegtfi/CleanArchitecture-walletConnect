package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

data class EndMeetingResponse(
    val returnCode: ReturnCode,
    val messageKey: String,
    val message: String,
)