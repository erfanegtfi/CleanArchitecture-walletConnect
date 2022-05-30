package io.vaiyo.domain.model.response

import io.vaiyo.domain.enums.ReturnCode

data class IsMeetingRunningResponse(
    val returnCode: ReturnCode,
    val running: String?= null,
)